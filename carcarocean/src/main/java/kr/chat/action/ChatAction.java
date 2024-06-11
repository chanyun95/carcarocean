package kr.chat.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.item.dao.ItemDao;
import kr.member.dao.MemberDao;
import kr.member.vo.MemberVo;

public class ChatAction implements Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {
        	return "/WEB-INF/views/common/logout.jsp";
        }
		
		int item_num = Integer.parseInt(request.getParameter("item_num"));
		
		ItemDao itemDao = ItemDao.getDao();
		int chat_receiver = itemDao.getItem(item_num).getMember().getMem_num();
		
		request.setAttribute("item_num", item_num);
		
		if(request.getParameter("chat_receiver")!=null && request.getParameter("chat_giver")!=null) {
			request.setAttribute("chat_receiver", Integer.parseInt(request.getParameter("chat_receiver")));
			request.setAttribute("chat_giver", Integer.parseInt(request.getParameter("chat_giver")));
		} else {
			request.setAttribute("chat_receiver", chat_receiver);
			request.setAttribute("chat_giver", user_num);
		}
		MemberDao memberDao = MemberDao.getDao();
		MemberVo user = memberDao.getMember(user_num);
		request.setAttribute("user", user);
		
		return "/WEB-INF/views/chat/chat.jsp";
	}

}
