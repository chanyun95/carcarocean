package kr.buy.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.car.dao.CarDao;
import kr.car.vo.CarVO;
import kr.controller.Action;
import kr.util.PagingUtil;

public class MainBuyAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		
		request.setCharacterEncoding("utf-8");
		
		// 확인 끝
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null) {
			pageNum="1";
		}
		String keyfield = request.getParameter("keyfield");
		String keyword = request.getParameter("keyword");
		
		
		CarDao cDao = CarDao.getDao();
		int count = cDao.getListCarCount(keyfield, keyword, 1);

		PagingUtil page = new PagingUtil(keyfield, keyword, Integer.parseInt(pageNum),count,9,10,"mainBuy.do");
		
		List<CarVO> carList = null;
		if(count>0) {
			
			carList = cDao.getListCar(page.getStartRow(), page.getEndRow(), keyfield, keyword, 1, user_num);
		}
		
		request.setAttribute("count", count);
		request.setAttribute("carList", carList);
		request.setAttribute("page", page.getPage());
		request.setAttribute("keyfield", keyfield);
		
		return "/WEB-INF/views/buy/mainBuy.jsp";
	}
}
