package kr.b_re.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.b_re.dao.B_ReDao;
import kr.b_re.vo.B_ReVo;
import kr.controller.Action;

public class WriteFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");

		if(user_num == null) {
			return "redirect:/member/loginForm.do";
		}
		
		B_ReDao dao = B_ReDao.getDao();

	    List<B_ReVo> buyList = dao.getBuyList(user_num);
	    
	    request.setAttribute("buyList", buyList);
	    
		return "/WEB-INF/views/b_re/writeForm.jsp";
	}

}
