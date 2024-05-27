package kr.sell.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.sell.dao.SellDao;
import kr.sell.vo.SellVo;

public class InsertSellAction implements Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//로그인 체크
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {
			return "redirect:/member/loginForm.do";
		}
		
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
		sell.setMem_num(user_num);
		
		SellDao dao = SellDao.getDao();
		dao.insertSell(sell);
		
		return "/WEB-INF/views/sell/insertSellResult.jsp";
	}
}
