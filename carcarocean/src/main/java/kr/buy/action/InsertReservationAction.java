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

public class InsertReservationAction implements Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,String> mapAjax = new HashMap<>();
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {
			mapAjax.put("result", "logout");
		}else {
			BuyVo buy = new BuyVo();
			BuyDao dao = BuyDao.getDao();
			int car_num = Integer.parseInt(request.getParameter("car_num"));
			if(dao.checkBuy(car_num)) {
				// 차량 예약 완료로 변경
				CarDao carDao = CarDao.getDao();
				carDao.updateCarStatus(car_num,1);
				
				// 구매 예약 정보 등록
				
				buy.setMem_num(user_num);
				buy.setCar_num(car_num);
				
				dao.insertReservation(buy);
				
				mapAjax.put("result","success");
			}else {
				mapAjax.put("result","fail");
			}
		}  
		
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
