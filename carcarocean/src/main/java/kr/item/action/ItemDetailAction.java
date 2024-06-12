package kr.item.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.chat.dao.ChatDao;
import kr.controller.Action;
import kr.item.dao.ItemDao;
import kr.item.vo.ItemVo;
import kr.member.dao.MemberDao;
import kr.member.vo.MemberVo;
import kr.util.DurationFromNow;
import kr.util.PagingUtil;
import kr.util.ShopUtil;
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
		
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null) {
			pageNum="1";
		}

		List<ItemVo> itemList =	itemDao.getItemListfavored(1,9,0);
		
		ChatDao chatDao = ChatDao.getDao();
		int itemChatCount = chatDao.itemChatCount(item_num);
		
		request.setAttribute("itemChatCount", itemChatCount);
		//중고거래 저장(인기매물순서)
		request.setAttribute("itemList", itemList);
		request.setAttribute("mem_grade", ShopUtil.getGrade(item.getMember().getMem_grade()));
		request.setAttribute("item", item);
		return "/WEB-INF/views/item/itemDetail.jsp";
	}
}
