package kr.chat.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.chat.dao.ChatDao;
import kr.controller.Action;
import kr.member.dao.MemberDao;
import kr.member.vo.MemberVo;

public class ChatListAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {
			return "/WEB-INF/views/common/logout.jsp";
		}
		
		int item_num = Integer.parseInt(request.getParameter("item_num"));

		ChatDao chatDao = ChatDao.getDao();
//		List<ChatVo> chatList = chatDao.getAllListChat(item_num, user_num);

		MemberDao memberDao = MemberDao.getDao();
		
//		for(int i=0; i<chatList.size(); i++) {
//			ChatVo chat = chatList.get(i);
//			chat.setReceiver(memberDao.getMember(chat.getReceiver().getMem_num()));
//			chat.setGiver(memberDao.getMember(chat.getGiver().getMem_num()));
//		}
		List<Integer> memberNumberList = chatDao.getSpecificList(item_num, user_num);
		List<MemberVo> memberList = new ArrayList<>();
		for (int i = 0; i < memberNumberList.size(); i++) {
			MemberVo member = new MemberVo();
			member = memberDao.getMember(memberNumberList.get(i));
			memberList.add(member);
		}
		
		request.setAttribute("item_num", item_num);
		request.setAttribute("memberList", memberList);
//		request.setAttribute("chatList", chatList);
		
		return "/WEB-INF/views/chat/chatList.jsp";
	}

}
