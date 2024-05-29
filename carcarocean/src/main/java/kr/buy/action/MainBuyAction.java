package kr.buy.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.car.dao.CarDao;
import kr.car.vo.CarVO;
import kr.controller.Action;

public class MainBuyAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		CarDao cDao = CarDao.getDao();
		// 판매중인 차량, 판매 완료 차량 모두
		List<CarVO> carList = cDao.getListCar(1, 12, null, null, 1);
		
		request.setAttribute("carList", carList);
		
		return "/WEB-INF/views/buy/mainBuy.jsp";
	}
}
