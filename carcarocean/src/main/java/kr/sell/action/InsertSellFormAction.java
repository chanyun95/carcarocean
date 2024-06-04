package kr.sell.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.MemberDao;
import kr.member.vo.MemberVo;

public class InsertSellFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//로그인 체크
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {
			return "/WEB-INF/views/sell/logout.jsp";
		}
		MemberDao memberDao = MemberDao.getDao();
		MemberVo member = memberDao.getMember(user_num);
		request.setAttribute("member", member);
		
		//로그인이 되어 있을 때
		return "/WEB-INF/views/sell/insertSellForm.jsp";
	}
	
}
