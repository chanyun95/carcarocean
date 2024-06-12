package kr.sell.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.car.dao.CarDao;
import kr.car.vo.CarVO;
import kr.controller.Action;
import kr.s_re.dao.S_ReDao;
import kr.s_re.vo.S_ReVo;

public class MainSellAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//최근 등록된 차량 보여주기
		CarDao cDao = CarDao.getDao();
		//판매중인 리스트만
		List<CarVO>carList = cDao.getListCar(1, 3, null, null, 0,null);
		request.setAttribute("carList", carList);
		
		S_ReDao s_reDao = S_ReDao.getDao();
		List<S_ReVo> s_reList = s_reDao.getListSellReviewMain(0, 6);
		request.setAttribute("s_reList", s_reList);
		
		return "/WEB-INF/views/sell/mainSell.jsp";
	}

}
