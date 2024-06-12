package kr.favorite_car.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.favorite_car.dao.Favorite_carDao;

public class RemoveFavAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		
		if(user_num==null) {
			return "/WEB-INF/views/member/loginForm.jsp";
		}
		Favorite_carDao favDao = Favorite_carDao.getDao();
		
		int car_num = Integer.parseInt(request.getParameter("car_num"));
		
		favDao.removeFav(car_num, user_num);
		
		return "redirect:/fav/myFavList.do";
	}
}
