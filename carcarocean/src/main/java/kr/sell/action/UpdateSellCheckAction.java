package kr.sell.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.sell.dao.SellDao;

public class UpdateSellCheckAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//로그인 체크&관리자 체크
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		//로그인 안되어 있을 시 로그인 폼으로 이동
		if(user_num==null) {
			return "/WEB-INF/views/common/logout.jsp";
		}
		//관리자아닌데 잘못된 접근시 main.do로 이동
		if(user_auth!=9) {
			return "/WEB-INF/views/common/warningPage.jsp";
		}
		
		int sell_num = Integer.parseInt(request.getParameter("sell_num"));
		int sell_check = Integer.parseInt(request.getParameter("sell_check"));
		
		SellDao dao = SellDao.getDao();
		dao.updateSellCheck(sell_num,sell_check);
		return "redirect:/sell/adminSellDetail.do?sell_num="+sell_num;
	}
}