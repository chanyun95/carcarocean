package kr.sell.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.sell.dao.SellDao;
import kr.sell.vo.SellVo;

public class InsertSellAction implements Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		SellVo sell = new SellVo();
		
		sell.setSell_mile(Integer.parseInt(request.getParameter("sell_mile")));
		sell.setSell_cnumber(request.getParameter("sell_cnumber"));
		sell.setSell_maker(request.getParameter("sell_maker"));
		sell.setSell_cname(request.getParameter("sell_cname"));
		sell.setSell_name(request.getParameter("sell_name"));
		sell.setSell_phone(request.getParameter("sell_phone"));
		sell.setSell_place1(request.getParameter("sell_place1"));
		sell.setSell_place2(request.getParameter("sell_place2"));
		sell.setSell_date(Integer.parseInt(request.getParameter("sell_date")));
		/* sell.setMem_num(Integer.parseInt(request.getParameter("user_num"))); */
		sell.setMem_num(5);
		
		SellDao dao = SellDao.getDao();
		dao.insertSell(sell);
		
		return null;
	}

}
