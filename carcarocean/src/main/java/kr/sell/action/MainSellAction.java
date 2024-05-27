package kr.sell.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.car.dao.CarDao;
import kr.car.vo.CarVO;
import kr.controller.Action;

public class MainSellAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//최근 등록된 차량 보여주기
		CarDao cDao = CarDao.getDao();
		List<CarVO>carList = cDao.getListCar(1, 3, null, null, -1);
		request.setAttribute("carList", carList);
		
		return "/WEB-INF/views/sell/mainSell.jsp";
	}

}
