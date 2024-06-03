package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.MemberDao;
import kr.member.vo.MemberVo; 

public class ModifyUserFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = 
				(Integer)session.getAttribute("user_num");
		if(user_num == null) {//로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		 
		//로그인이 된 경우
		MemberDao dao = MemberDao.getDao();
		MemberVo member = dao.getMember(user_num);
		
		request.setAttribute("member", member);
		//JSP 경로 반환
		return "/WEB-INF/views/member/modifyUserForm.jsp";
	}
}

