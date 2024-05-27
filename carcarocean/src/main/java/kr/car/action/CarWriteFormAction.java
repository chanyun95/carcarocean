package kr.car.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.checker.dao.CheckerDao;
import kr.checker.vo.CheckerVo;
import kr.controller.Action;
import kr.sell.dao.SellDao;
import kr.sell.vo.SellVo;

public class CarWriteFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		/*
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
		
		request.setAttribute("user_num", user_num);
		request.setAttribute("user_auth", user_auth);
		//관리자로 로그인한 경우
		 */
		
		int sell_check = Integer.parseInt(request.getParameter("sell_check"));
		int sell_num = Integer.parseInt(request.getParameter("sell_num"));
		SellDao sDao = SellDao.getDao();
		SellVo sell = sDao.getSell(sell_num);
		
		//검수자 정보 보내야함
		CheckerDao cDao = CheckerDao.getDao();
		List<CheckerVo>checkerList = cDao.getCheckerAllList();
		
		request.setAttribute("checkerList", checkerList);
		request.setAttribute("sell_check", sell_check);
		request.setAttribute("sell_num", sell_num);
		request.setAttribute("sell", sell);
		
		return "/WEB-INF/views/car/carWriteForm.jsp";
	}

}
