package kr.sell.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.sell.dao.SellDao;
import kr.sell.vo.SellVo;

public class AdminSellDetailAction implements Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int sell_num = Integer.parseInt(request.getParameter("sell_num"));
		
		SellDao dao = SellDao.getDao();
		
		SellVo sell = dao.getSell(sell_num);
		
		request.setAttribute("sell", sell);
		
		return "/WEB-INF/views/sell/adminSellDetail.jsp";
	}
}
