package kr.favorite_car.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.buy.dao.BuyDao;
import kr.buy.vo.BuyVo;
import kr.car.dao.CarDao;
import kr.controller.Action;
import kr.favorite_car.dao.Favorite_carDao;
import kr.favorite_car.vo.Favorite_carVo;

public class FavCarAction implements Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,String> mapAjax = new HashMap<>();
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {
			mapAjax.put("result", "logout");
		}else {
			int car_num = Integer.parseInt(request.getParameter("car_num"));
			
			Favorite_carDao fcDao = Favorite_carDao.getDao();
			Favorite_carVo fc = fcDao.getFc(car_num,user_num);
			if(fc==null) {
				fcDao.insertFav(car_num, user_num);
				mapAjax.put("action", "insertFav");
			}else {
				fcDao.removeFav(car_num, user_num);
				mapAjax.put("action", "removeFav");
			}
			mapAjax.put("result", "success");
		}
		
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
