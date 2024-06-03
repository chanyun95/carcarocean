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
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		
		//글 번호 반환
		int b_re_num = Integer.parseInt(request.getParameter("b_re_num"));
		
		B_ReDao dao = B_ReDao.getDao();
		
		B_ReVo b_re = dao.getB_Re(b_re_num);
		/*int buy_num = b_re.getBuy_num();
		
		 * BuyDao buyDao = BuyDao.getDao(); 
		 * BuyVo buy = buyDao.getBuy(buy_num);
		 * 
		 * CarDao carDao = CarDao.getDao(); 
		 * CarVO car = carDao.getCar(buy.getCar_num());
		 */
		request.setAttribute("user_num", user_num);
		request.setAttribute("b_re", b_re);
		/*
		 * request.setAttribute("buy", buy); 
		 * request.setAttribute("car", car);
		 */
		
		return "/WEB-INF/views/b_re/detail.jsp";
	}

}
