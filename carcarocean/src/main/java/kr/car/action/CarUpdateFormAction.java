package kr.car.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.car.dao.CarDao;
import kr.car.vo.CarVO;
import kr.checker.dao.CheckerDao;
import kr.checker.vo.CheckerVo;
import kr.controller.Action;

public class CarUpdateFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//로그인 체크&관리자 체크
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		//로그인 안되어 있을 시 로그인 폼으로 이동
		if(user_num==null) {
			return "redirect:/member/loginForm.do";
		}
		//관리자아닌데 잘못된 접근시 main.do로 이동
		if(user_auth!=9) {
			return "/WEB-INF/views/common/warningPage";
		}

		int car_num = Integer.parseInt(request.getParameter("car_num"));
		
		CarDao cDao  = CarDao.getDao();
		CarVO car = cDao.getCar(car_num);
		
		request.setAttribute("car", car);
		
		CheckerDao checkerDao = CheckerDao.getDao();
		List<CheckerVo> checkerList = checkerDao.getCheckerAllList();
		
		request.setAttribute("checkerList", checkerList);
		
		return "/WEB-INF/views/car/carUpdateForm.jsp";
	}

}
