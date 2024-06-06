package kr.item.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.item.dao.ItemDao;
import kr.item.vo.ItemVo;
import kr.member.dao.MemberDao;
import kr.member.vo.MemberVo;

public class ItemDetailAction implements Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int item_num = Integer.parseInt(request.getParameter("item_num"));
		ItemDao itemDao = ItemDao.getDao();
		ItemVo item = itemDao.getItem(item_num);
		request.setAttribute("item", item);
		return "/WEB-INF/views/item/itemDetail.jsp";
	}
}
