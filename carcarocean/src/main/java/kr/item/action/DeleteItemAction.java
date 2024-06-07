package kr.item.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.item.dao.ItemDao;
import kr.item.vo.ItemVo;
import kr.util.FileUtil;

public class DeleteItemAction implements Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {
			return "/WEB-INF/views/common/logout.jsp";
		}
		int item_num = Integer.parseInt(request.getParameter("item_num"));
		ItemDao itemDao = ItemDao.getDao();
		ItemVo item = itemDao.getItem(item_num);
		if(item.getMember().getMem_num()!=user_num) {
			return "/WEB-INF/views/common/warningPage.jsp";
		}
		
		String[] fileArr = item.getItem_photo().split(",");
		for(String fileName:fileArr) {
			FileUtil.removeFile(request, fileName);
		}
		
		itemDao.deleteItem(item_num);
		
		return "redirect:/item/itemList.do";
	}

}
