package kr.sell.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.sell.dao.SellDao;

public class DeleteSellAction implements Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
				
		int sell_num = Integer.parseInt(request.getParameter("sell_num"));
		
		SellDao dao = SellDao.getDao();
		
		dao.deleteSell(sell_num);
		 
		return "redirect:/sell/adminSellList.do";
	}
}
