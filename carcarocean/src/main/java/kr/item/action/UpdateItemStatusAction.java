package kr.item.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.item.dao.ItemDao;
import kr.item.vo.ItemVo;
import kr.util.FileUtil;
import kr.util.StringUtil;

public class UpdateItemStatusAction implements Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {
			return "/WEB-INF/views/common/logout.jsp";
		}
		int item_num = Integer.parseInt(request.getParameter("item_num"));
		ItemDao itemDao = ItemDao.getDao();
		ItemVo item = itemDao.getItem(item_num);
		item.setItem_detail(StringUtil.useBrHtml(item.getItem_detail()));
		if(item.getMember().getMem_num()!=user_num) {
			return "/WEB-INF/views/common/warningPage.jsp";
		}
		
		if(item.getItem_photo()!=null) {
			String[] fileArr = item.getItem_photo().split(",");
			for(String fileName:fileArr) {
				FileUtil.removeFile(request, fileName);
			}
		}
		
		itemDao.updateItemStatus(item_num, 1);
		
		return "redirect:/item/itemList.do";
	}

}
