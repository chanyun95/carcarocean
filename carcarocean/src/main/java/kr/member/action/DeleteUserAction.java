package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.MemberDao;
import kr.member.vo.MemberVo;
import kr.util.FileUtil;

public class DeleteUserAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = 
				(Integer)session.getAttribute("user_num");
		if(user_num == null) {//로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		
		//로그인 된 경우
		//전송된 데이터 인코딩 타입 지정
		request.setCharacterEncoding("utf-8");
		//전송된 데이터 반환
		String mem_id = request.getParameter("mem_id");
		String mem_email = request.getParameter("mem_email");
		String mem_passwd = request.getParameter("mem_passwd");
		//로그인한 아이디
		String user_id = (String)session.getAttribute("user_id");
		
		MemberDao dao = MemberDao.getDao();
		MemberVo db_member = dao.checkMember(mem_id);
		boolean check = false;
		//사용자가 입력한 아이디가 존재하고 로그인한 아이디와 일치하는지 체크,
		//입력한 이메일과 저장된 이메일 일치 여부 체크
		if(db_member!=null && mem_id.equals(user_id) 
				&& mem_email.equals(db_member.getMem_email()) && mem_passwd.equals(db_member.getMem_passwd())) {
			//비밀번호 일치 여부 체크
			check = true;
		}
		if(check) {//인증 성공
			//회원정보 삭제
			dao.deleteMember(user_num);
			//프로필 사진 삭제
			FileUtil.removeFile(request, db_member.getMem_photo());
			//로그아웃
			session.invalidate();
		}
		request.setAttribute("check", check);
		//JSP 경로 반환
		return "/WEB-INF/views/member/deleteUser.jsp";
	}

}




