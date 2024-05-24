package kr.car.action;

import java.sql.Date;
import java.text.SimpleDateFormat;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.car.dao.CarDao;
import kr.car.vo.CarVO;
import kr.controller.Action;
import kr.util.FileUtil;

public class CarWriteAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = 
				(Integer)session.getAttribute("user_num");
		if(user_num == null) { //로그인 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		if(user_auth != 9) { //관리자로 로그인하지 않은 경우
			return "/WEB-INF/views/common/notice.jsp";
		}
		//관리자로 로그인한 경우
		//전송된 데이터 인코딩 타입 지정
		request.setCharacterEncoding("utf-8");
		CarVO car = new CarVO();
		/* SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); */
		car.setCar_maker(request.getParameter("car_maker"));
		car.setCar_name(request.getParameter("car_name"));
		car.setCar_size(Integer.parseInt(request.getParameter("car_size")));
		car.setCar_birth(request.getParameter("car_birth"));
		car.setCar_cc(Integer.parseInt(request.getParameter("car_cc")));
		car.setCar_fuel_type(Integer.parseInt(request.getParameter("car_fuel_type")));
		car.setCar_fuel_efficiency(Integer.parseInt(request.getParameter("car_fuel_efficiency")));
		car.setCar_mile(Integer.parseInt(request.getParameter("car_mile")));
		car.setCar_price(Integer.parseInt(request.getParameter("car_price")));
		car.setCar_color(request.getParameter("car_color"));
		car.setCar_photo(FileUtil.createFile(request, "car_photo"));
		car.setCar_auto(Integer.parseInt(request.getParameter("car_auto")));
		car.setCar_use(Integer.parseInt(request.getParameter("car_use")));
		car.setCar_accident(request.getParameter("car_accident"));
		car.setCar_owner_change(Integer.parseInt(request.getParameter("car_owner_change")));
		car.setCar_design_op(request.getParameter("car_design_op"));
		car.setCar_con_op(request.getParameter("car_con_op"));
		car.setCar_drive_op(request.getParameter("car_drive_op"));
		
		CarDao dao = CarDao.getDao();
		dao.insertCar(car);
		
		String url = request.getContextPath() + "/carBoard/carBuyList.do";    //차량 검수대기목록
		response.addHeader("Refresh", "2;url="+url);    //2초
		request.setAttribute("result_title", "상품 등록 완료");
		request.setAttribute("result_msg", "성공적으로 등록되었습니다.");
		request.setAttribute("result_url", url);
		
		return "/WEB-INF/views/common/result_view.jsp";
	}

}
