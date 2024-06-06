package kr.item.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;

public class InsertItemFormAction implements Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		if(user_num==null) {
			return "redirect:/member/loginForm.do";
		}
		
		//탈퇴 or 정지회원 금지
		if(user_auth<2) {
			return "redirect:/main/main.do";
		}
		
		
		return "/WEB-INF/views/item/insertItemForm.jsp";
	}
}
