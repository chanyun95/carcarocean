package kr.buy.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.car.dao.CarDao;
import kr.car.vo.CarVO;
import kr.checker.dao.CheckerDao;
import kr.checker.vo.CheckerVo;
import kr.controller.Action;
import kr.favorite_car.dao.Favorite_carDao;
import kr.favorite_car.vo.Favorite_carVo;
import kr.member.dao.MemberDao;
import kr.member.vo.MemberVo;
import kr.util.ShopUtil;

public class BuyDetailAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		int car_num = Integer.parseInt(request.getParameter("car_num"));
		CarDao carDao = CarDao.getDao();
		CarVO car = carDao.getCar(car_num);
		request.setAttribute("car", car);
		
		CheckerDao checkerDao = CheckerDao.getDao();
		CheckerVo checker = checkerDao.getChecker(car.getChecker_num());
		request.setAttribute("checker", checker);
		
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num!=null) {
			//관심차량 체크
			Favorite_carDao favDao = Favorite_carDao.getDao();
			Favorite_carVo fav = favDao.getFc(car_num, user_num);
			request.setAttribute("fav", fav);
		}
		
		Integer user_grade = (Integer)session.getAttribute("user_grade");
		if(user_grade!=null) {
			request.setAttribute("getDiscountFee", ShopUtil.getDiscountFee(user_grade, car.getCar_price()));
		}
		
		return "/WEB-INF/views/buy/buyDetail.jsp";
	}

}
