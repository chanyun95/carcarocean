package kr.chat.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;

public class ChatAction implements Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {
        	return "/WEB-INF/views/common/logout.jsp";
        }
		
		int item_num = Integer.parseInt(request.getParameter("item_num"));
		request.setAttribute("item_num", item_num);
		
		String chat_giver = request.getParameter("chat_giver");
		if(chat_giver!=null) {
			request.setAttribute("chat_giver", chat_giver);
		} else {
			request.setAttribute("chat_giver", user_num);
		}
		return "/WEB-INF/views/chat/chat.jsp";
	}

}
