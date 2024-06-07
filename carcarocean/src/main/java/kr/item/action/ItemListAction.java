package kr.item.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.item.dao.ItemDao;
import kr.item.vo.ItemVo;
import kr.util.DurationFromNow;
import kr.util.PagingUtil;

public class ItemListAction implements Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		// 확인 끝
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null) {
			pageNum="1";
		}
		String keyfield = request.getParameter("keyfield");
		String keyword = request.getParameter("keyword");
		
		ItemDao itemDao = ItemDao.getDao();
		int count = itemDao.getItemListCount(keyfield, keyword);

		PagingUtil page = new PagingUtil(keyfield, keyword, Integer.parseInt(pageNum),count,12,10,"itemList.do");
		
		List<ItemVo> itemList = null;
		if(count>0) {
			itemList = itemDao.getItemList(page.getStartRow(), page.getEndRow(), keyfield, keyword);
		}
		
		for(ItemVo item : itemList) {
			item.setItem_reg(DurationFromNow.getTimeDiffLabel(item.getItem_reg()));
		}
		
		request.setAttribute("count", count);
		request.setAttribute("itemList", itemList);
		request.setAttribute("page", page.getPage());
		request.setAttribute("keyfield", keyfield);
		return "/WEB-INF/views/item/itemList.jsp";
	}
}