package kr.chat.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import kr.chat.dao.ChatDao;
import kr.chat.vo.ChatVo;
import kr.controller.Action;
import kr.item.dao.ItemDao;
import kr.member.dao.MemberDao;
import kr.member.vo.MemberVo;

public class GetChatAjaxAction implements Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,Object> map = new HashMap<>();
		
		request.setCharacterEncoding("utf-8");
		
		int item_num = Integer.parseInt(request.getParameter("item_num"));
		ItemDao itemDao = ItemDao.getDao();
		int chat_receiver = itemDao.getItem(item_num).getMember().getMem_num();
		MemberDao memberDao = MemberDao.getDao();
		MemberVo receiver = memberDao.getMember(chat_receiver);
		map.put("receiver", receiver);
		
		//보내는 사람
		int chat_giver = Integer.parseInt(request.getParameter("chat_giver"));
		MemberVo giver = memberDao.getMember(chat_giver);
		map.put("giver", giver);
		
		ChatDao chatDao = ChatDao.getDao();
		List<ChatVo> chatList = chatDao.getListChat(item_num,chat_receiver, chat_giver);
		
		map.put("chatList", chatList);
		
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(map);
		
		request.setAttribute("ajaxData", ajaxData);
		return "/WEB-INF/views/common/ajax_view.jsp";
	}
}
