package kr.sell.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.sell.dao.SellDao;
import kr.sell.vo.SellVo;
import kr.util.PagingUtil;

public class MySellListAction implements Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {
			return "/WEB-INF/views/common/logout.jsp";
		}
		request.setCharacterEncoding("utf-8");
		
		// 확인 끝
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null) {
			pageNum="1";
		}
		
		String keyfield = request.getParameter("keyfield");
		String keyword = request.getParameter("keyword");
		String sell_check = request.getParameter("sell_check");
		//초기에 sell_check없이 검색할 때
		if(sell_check==null) {
			sell_check="0";
		}
		
		SellDao sellDao = SellDao.getDao();
		int count = sellDao.getSellListCount(keyfield, keyword, sell_check, user_num);
		
		//페이지 처리
		PagingUtil page = new PagingUtil(keyfield, keyword, Integer.parseInt(pageNum),count,10,10,"mySellList.do");
		
		List<SellVo> list = null;
		if(count > 0) {
			list = sellDao.getSellList(page.getStartRow(), page.getEndRow(), keyfield, keyword, sell_check, user_num);
		}
		
		request.setAttribute("count", count);
		request.setAttribute("list", list);
		request.setAttribute("page", page.getPage());
		request.setAttribute("keyfield", keyfield);
		request.setAttribute("sell_check", sell_check);
		
		
		return "/WEB-INF/views/sell/mySellList.jsp";
	}

}
