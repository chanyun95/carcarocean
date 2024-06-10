package kr.chat.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.chat.dao.ChatDao;
import kr.chat.vo.ChatVo;
import kr.controller.Action;
import kr.item.dao.ItemDao;
import kr.item.vo.ItemVo;

public class ChatAjaxAction implements Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,Object> map = new HashMap<>();
		
		request.setCharacterEncoding("utf-8");
		
		String message = request.getParameter("message");
		//상품번호
		int item_num = Integer.parseInt(request.getParameter("item_num"));
		
		ItemDao itemDao = ItemDao.getDao();
		ItemVo item = itemDao.getItem(item_num);
		
		//받는 사람
		int chat_receiver = item.getMember().getMem_num();
		
		
		//보내는 사람
		int chat_giver = Integer.parseInt(request.getParameter("chat_giver"));
		ChatDao chatDao = ChatDao.getDao();

		ChatVo chat = new ChatVo();
		chat.setChat_receiver(chat_receiver);
		chat.setChat_giver(chat_giver);
		chat.setItem_num(item_num);
		chat.setChat_message(message);
		
		chatDao.insertChat(chat);
		
		List<ChatVo> chatList = chatDao.getListChat(item_num, chat_receiver ,chat_giver);
		
		map.put("chatList", chatList);
		map.put("item_num", item_num);
		
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(map);
		
		request.setAttribute("ajaxData", ajaxData);
		return "/WEB-INF/views/common/ajax_view.jsp";
	}
	
}
