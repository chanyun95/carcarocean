package kr.buy.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.car.dao.CarDao;
import kr.car.vo.CarVO;
import kr.checker.dao.CheckerDao;
import kr.checker.vo.CheckerVo;
import kr.controller.Action;

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
		
		return "/WEB-INF/views/buy/buyDetail.jsp";
	}

}
