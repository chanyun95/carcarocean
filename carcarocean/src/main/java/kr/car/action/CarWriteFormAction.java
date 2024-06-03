package kr.car.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.checker.dao.CheckerDao;
import kr.checker.vo.CheckerVo;
import kr.controller.Action;
import kr.sell.dao.SellDao;
import kr.sell.vo.SellVo;

public class CarWriteFormAction implements Action{

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
			return "/WEB-INF/views/common/warningPage.jsp";
		}
		
		int sell_num = Integer.parseInt(request.getParameter("sell_num"));
		SellDao sDao = SellDao.getDao();
		SellVo sell = sDao.getSell(sell_num);
		
		//검수자 정보 보내야함
		CheckerDao cDao = CheckerDao.getDao();
		List<CheckerVo>checkerList = cDao.getCheckerAllList();
		
		request.setAttribute("checkerList", checkerList);
		request.setAttribute("sell", sell);
		
		return "/WEB-INF/views/car/carWriteForm.jsp";
	}

}
