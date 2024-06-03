package kr.buy.action;

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

public class InsertBuyAction implements Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,String> mapAjax = new HashMap<>();
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {
			mapAjax.put("result", "logout");
		}else {
			int car_num = Integer.parseInt(request.getParameter("car_num"));
			// 차량 판매 완료로 변경
			CarDao carDao = CarDao.getDao();
			carDao.updateCarStatus(car_num);
			
			// 구매 정보 등록
			BuyVo buy = new BuyVo();
			buy.setMem_num(user_num);
			buy.setCar_num(car_num);
			BuyDao dao = BuyDao.getDao();
			dao.insertBuy(buy);
			
			mapAjax.put("result","success");
		}
		
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
