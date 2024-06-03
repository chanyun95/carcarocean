package kr.favorite_car.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.favorite_car.dao.Favorite_carDao;
import kr.favorite_car.vo.Favorite_carVo;

public class GetFavAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Map<String,Object> mapAjax = new HashMap<String,Object>();
		
		HttpSession session = request.getSession();
		
		Integer user_num = (Integer)session.getAttribute("user_num");
		int car_num = Integer.parseInt(request.getParameter("car_num"));
		
		Favorite_carDao dao = Favorite_carDao.getDao();
		if(user_num==null) {
			mapAjax.put("show", "noFav");
		}else {//로그인 된 경우
			Favorite_carVo fc = dao.getFc(car_num,user_num);
			if(fc!=null) {
				mapAjax.put("show", "yesFav");
			}else {
				mapAjax.put("show", "noFav");
			}
		}
		
		//JSON 데이터 생성
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
