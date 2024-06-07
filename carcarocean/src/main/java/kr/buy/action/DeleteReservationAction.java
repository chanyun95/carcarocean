package kr.buy.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.buy.dao.BuyDao;
import kr.car.dao.CarDao;
import kr.controller.Action;

public class DeleteReservationAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {
			return "/WEB-INF/views/common/logout.jsp";
		}
		int car_num = Integer.parseInt(request.getParameter("car_num"));
		
		BuyDao buyDao = BuyDao.getDao();
		buyDao.deleteReservation(user_num, car_num);
		
		CarDao carDao = CarDao.getDao();
		carDao.updateCarStatus(car_num,0);
		request.setAttribute("car_num", car_num);
		
		return "/WEB-INF/views/buy/deleteReservationResult.jsp?car_num="+car_num;
		 
	}

}
