package kr.car.action;

import java.sql.Date;
import java.text.SimpleDateFormat;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.car.dao.CarDao;
import kr.car.vo.CarVO;
import kr.controller.Action;
import kr.sell.dao.SellDao;
import kr.util.FileUtil;

public class CarWriteAction implements Action{

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

		
		//전송된 데이터 인코딩 타입 지정
		request.setCharacterEncoding("utf-8");
		
		// 판매 정보 '검수 완료'로 변경
		int sell_check = 2;
		int sell_num = Integer.parseInt(request.getParameter("sell_num"));
		SellDao sellDao = SellDao.getDao();
		sellDao.updateSellCheck(sell_num, sell_check);
		
		// 검수 완료 됨 과 동시에 검수정보들 car에 저장
		CarVO car = new CarVO();
		/* SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); */
		car.setCar_maker(request.getParameter("car_maker"));
		car.setCar_name(request.getParameter("car_name"));
		car.setCar_size(Integer.parseInt(request.getParameter("car_size")));
		car.setCar_birth(request.getParameter("car_birth"));
		car.setCar_cc(Integer.parseInt(request.getParameter("car_cc")));
		car.setCar_fuel_type(Integer.parseInt(request.getParameter("car_fuel_type")));
		car.setCar_fuel_efficiency(Float.parseFloat(request.getParameter("car_fuel_efficiency")));
		car.setCar_mile(Integer.parseInt(request.getParameter("car_mile")));
		car.setCar_price(Integer.parseInt(request.getParameter("car_price")));
		car.setCar_color(request.getParameter("car_color"));
		// 다중 파일 처리
		car.setCar_photo(FileUtil.createFiles(request));
		car.setCar_auto(Integer.parseInt(request.getParameter("car_auto")));
		car.setCar_use(Integer.parseInt(request.getParameter("car_use")));
		car.setCar_accident(request.getParameter("car_accident"));
		car.setCar_owner_change(Integer.parseInt(request.getParameter("car_owner_change")));
		car.setCar_design_op(request.getParameter("car_design_op"));
		car.setCar_con_op(request.getParameter("car_con_op"));
		car.setCar_drive_op(request.getParameter("car_drive_op"));
		car.setChecker_num(Integer.parseInt(request.getParameter("checker_num")));
		car.setCar_checker_opinion(request.getParameter("car_checker_opinion"));
		
		CarDao dao = CarDao.getDao();
		dao.insertCar(car);
		
		
		return "redirect:/sell/adminSellList.do";
	}

}
