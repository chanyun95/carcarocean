package kr.s_re.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;

public class S_re_WriteFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		
		if(user_num == null) {//로그인이 되지 않는 경우
			return "/WEB-INF/views/member/loginForm.do";
		}
		//로그인 된 경우

		return "WEB-INF/views/s_re/s_re_writeForm.jsp";
	}

}
