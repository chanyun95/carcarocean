package kr.item.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.item.dao.ItemDao;
import kr.item.vo.ItemVo;
import kr.member.dao.MemberDao;
import kr.member.vo.MemberVo;
import kr.util.DurationFromNow;
import kr.util.StringUtil;

public class ItemDetailAction implements Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int item_num = Integer.parseInt(request.getParameter("item_num"));
		ItemDao itemDao = ItemDao.getDao();
		itemDao.viewItem(item_num);
		ItemVo item = itemDao.getItem(item_num);
		item.setItem_detail(item.getItem_detail());
		item.setItem_reg(DurationFromNow.getTimeDiffLabel(item.getItem_reg()));

		request.setAttribute("item", item);
		return "/WEB-INF/views/item/itemDetail.jsp";
	}
}
