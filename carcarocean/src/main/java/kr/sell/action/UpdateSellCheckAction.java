package kr.sell.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.sell.dao.SellDao;

public class UpdateSellCheckAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int sell_num = Integer.parseInt(request.getParameter("sell_num"));
		int sell_check = Integer.parseInt(request.getParameter("sell_check"));
		
		SellDao dao = SellDao.getDao();
		dao.updateSellCheck(sell_num,sell_check);
		return "redirect:/sell/adminSellDetail.do?sell_num="+sell_num;
	}
}
