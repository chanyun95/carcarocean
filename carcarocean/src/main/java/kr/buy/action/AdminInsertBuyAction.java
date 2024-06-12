package kr.buy.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.buy.dao.BuyDao;
import kr.car.dao.CarDao;
import kr.car.vo.CarVO;
import kr.controller.Action;
import kr.favorite_car.dao.Favorite_carDao;

public class AdminInsertBuyAction implements Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//로그인 체크&관리자 체크
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		//로그인 안되어 있을 시 로그인 폼으로 이동
		if(user_num==null) {
			return "/WEB-INF/views/common/logout.jsp";
		}
		//관리자아닌데 잘못된 접근시 main.do로 이동
		if(user_auth!=9) {
			return "/WEB-INF/views/common/warningPage.jsp";
		}
		
		int car_num = Integer.parseInt(request.getParameter("car_num"));
		int mem_num = Integer.parseInt(request.getParameter("mem_num"));
		
		CarDao carDao = CarDao.getDao();
		carDao.updateCarStatus(car_num,2);
		CarVO car = carDao.getCar(car_num);
		
		BuyDao buyDao = BuyDao.getDao();
		buyDao.insertBuy(car_num, mem_num,car.getCar_price());
		
		Favorite_carDao favDao = Favorite_carDao.getDao();
		favDao.removeAllFav(car_num);
		
		return "redirect:/buy/adminBuyList.do";
	}

}
