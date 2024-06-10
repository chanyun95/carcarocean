package kr.chat.action;

import java.util.HashMap;
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
import kr.member.dao.MemberDao;
import kr.member.vo.MemberVo;

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
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		
		
		ChatVo chat = new ChatVo();
		chat.setItem(item);
		//받는 사람
		MemberVo receiver = new MemberVo();
		receiver.setMem_num(chat_receiver);
		chat.setReceiver(receiver);
		//보내는 사람
		MemberVo giver = new MemberVo();
		giver.setMem_num(user_num);
		chat.setGiver(giver);
		
		chat.setChat_message(message);

		ChatDao chatDao = ChatDao.getDao();
		chatDao.insertChat(chat);
		
		map.put("item_num", item_num);
		map.put("chat_receiver", chat_receiver);
		map.put("chat_giver", user_num);
		
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(map);
		
		request.setAttribute("ajaxData", ajaxData);
		return "/WEB-INF/views/common/ajax_view.jsp";
	}
	
}
