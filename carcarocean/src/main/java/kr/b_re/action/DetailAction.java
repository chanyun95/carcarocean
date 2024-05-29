package kr.b_re.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.b_re.dao.B_ReDao;
import kr.b_re.vo.B_ReVo;
import kr.car.dao.CarDao;
import kr.car.vo.CarVO;
import kr.controller.Action;

public class DetailAction implements Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int b_re_num = Integer.parseInt(request.getParameter("b_re_num"));
		
		B_ReDao b_reDao = B_ReDao.getDao();
		B_ReVo b_re = b_reDao.getB_Re(b_re_num);
		int buy_num = b_re.getBuy_num();
		
		
		/*
		 * BuyDao buyDao = BuyDao.getDao(); 
		 * BuyVo buy = buyDao.getBuy(buy_num);
		 * 
		 * CarDao carDao = CarDao.getDao(); 
		 * CarVO car = carDao.getCar(buy.getCar_num());
		 */
	
		request.setAttribute("b_re", b_re);
		/*
		 * request.setAttribute("buy", buy); 
		 * request.setAttribute("car", car);
		 */
		
		return "/WEB-INF/views/b_re/detail.jsp";
	}

}
