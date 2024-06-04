package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.MemberDao;
import kr.member.vo.MemberVo;

public class LoginAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//전송된 데이터 인코딩 타입 지정
		request.setCharacterEncoding("utf-8");
		//전송된 데이터 반환
		String mem_id = request.getParameter("mem_id");
		String mem_passwd = request.getParameter("mem_passwd");
		
		MemberDao dao = MemberDao.getDao();
		MemberVo member = dao.checkMember(mem_id);
		boolean check = false;
		
		if(member!=null) {//동일한 id 존재
			//비밀번호 일치 여부 체크
			check = member.isCheckedPassword(mem_passwd);
			//정지회원의 경우 상태 표시
			request.setAttribute("mem_auth", member.getMem_auth());
		}
		if(check) {//인증 성공
			//로그인 처리
			HttpSession session = request.getSession();
			session.setAttribute("user_num", 
					                   member.getMem_num());
			session.setAttribute("user_id", member.getMem_id());
			session.setAttribute("user_auth", member.getMem_auth());
			session.setAttribute("user_grade", member.getMem_grade());
			session.setAttribute("user_photo", member.getMem_photo());
			//메인으로 리다이렉트
			return "redirect:/main/main.do";
		}
		//인증 실패
		return "/WEB-INF/views/member/login.jsp";
	}

}






