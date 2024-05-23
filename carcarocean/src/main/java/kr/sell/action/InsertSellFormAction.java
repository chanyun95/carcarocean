package kr.sell.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;

public class InsertSellFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		/*
		 * HttpSession session = request.getSession();
		 * Integer user_num = (Integer)session.getAttribute("user_num"); //로그인이 안 되어 있을 때
		 * if(user_num==null) { return "/WEB-INF/member/loginForm.jsp"; }
		 */
		
		//로그인이 되어 있을 때
		return "/WEB-INF/views/sell/insertSellForm.jsp";
	}
	
}
