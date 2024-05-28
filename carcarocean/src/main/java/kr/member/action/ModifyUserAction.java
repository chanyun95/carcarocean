package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.MemberDao;
import kr.member.vo.MemberVo;

public class ModifyUserAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = 
				(Integer)session.getAttribute("user_num");
		if(user_num == null) {//로그인이 안 된 경우
			return "redirect:/member/loginForm.do"; 
		}
		//로그인 된 경우
		//전송된 데이터 인코딩 타입 지정
		request.setCharacterEncoding("utf-8");
		
		String mem_id = request.getParameter("mem_id");
		//현재 비밀번호
		String origin_passwd = 
				request.getParameter("origin_passwd");
		String mem_passwd = request.getParameter("mem_passwd");
		//현재 로그인 한 아이디
		String user_id = 
				(String)session.getAttribute("user_id");
		
		MemberDao dao = MemberDao.getDao();
		MemberVo membercheck = dao.checkMember(mem_id);
		boolean check = false;
		
		//사용자가 입력한 아이디가 존재하고 로그인한 아이디와 일치하는지 체크
		if(membercheck!=null && mem_id.equals(user_id)) {
			//비밀번호 일치 여부 체크
			check = membercheck.isCheckedPassword(origin_passwd);
		}
		
		MemberVo member = new MemberVo();
		if(check) {
		member.setMem_num(user_num);//회원번호
		member.setMem_name(request.getParameter("mem_name"));
		member.setMem_phone(request.getParameter("mem_phone"));
		member.setMem_passwd(request.getParameter("mem_passwd"));
		member.setMem_email(request.getParameter("mem_email"));
		member.setMem_zipcode(request.getParameter("mem_zipcode"));
		member.setMem_address1(request.getParameter("mem_address1"));
		member.setMem_address2(request.getParameter("mem_address2"));
		dao.updateMember(member);
		request.setAttribute("notice_msg", "회원정보 수정 완료");
		request.setAttribute("notice_url", 
			  request.getContextPath()+"/member/myPage.do");
		
		}
		if(!check) {
			request.setAttribute("notice_msg", "아이디 또는 비밀번호 불일치");
			request.setAttribute("notice_url", 
				  request.getContextPath()+"/member/myPage.do");
		}
		//JSP 경로 반환
		return "/WEB-INF/views/common/alert_view.jsp";
	}
}