package kr.car.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.car.dao.CarDao;
import kr.car.vo.CarVO;
import kr.controller.Action;

public class CarBuyListAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		CarDao dao = CarDao.getDao();
		List<CarVO> carList = dao.getListCar(1, 9, null, null, 0);
		
		request.setAttribute("carList", carList);
		
		return "/WEB-INF/views/car/carBuyList.jsp";
	}

}
