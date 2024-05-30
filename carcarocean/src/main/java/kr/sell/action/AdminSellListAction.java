package kr.sell.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.sell.dao.SellDao;
import kr.sell.vo.SellVo;
import kr.util.PagingUtil;

public class AdminSellListAction implements Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//관리자인지 확인
		//로그인 체크&관리자 체크
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		//로그인 안되어 있을 시 로그인 폼으로 이동
		if(user_num==null) {
			return "redirect:/member/loginForm.do";
		}
		//관리자아닌데 잘못된 접근시 main.do로 이동
		if(user_auth!=9) {
			return "/WEB-INF/views/common/warningPage.jsp";
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
		
		SellDao dao = SellDao.getDao();
		int count = dao.getSellListCount(keyfield, keyword, sell_check);
		
		//페이지 처리
		PagingUtil page = new PagingUtil(keyfield, keyword, Integer.parseInt(pageNum),count,15,10,"adminSellList.do");
		
		List<SellVo> list = null;
		if(count > 0) {
			list = dao.getSellList(page.getStartRow(), page.getEndRow(), keyfield, keyword, sell_check);
		}
		
		request.setAttribute("count", count);
		request.setAttribute("list", list);
		request.setAttribute("page", page.getPage());
		request.setAttribute("keyfield", keyfield);
		request.setAttribute("sell_check", sell_check);
		
		return "/WEB-INF/views/sell/adminSellList.jsp";
	}

}
