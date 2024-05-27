package kr.checker.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.checker.dao.CheckerDao;
import kr.checker.vo.CheckerVo;
import kr.controller.Action;

public class AdminCheckerDetailAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int checker_num = Integer.parseInt(request.getParameter("checker_num"));
		CheckerDao dao = CheckerDao.getDao();
		CheckerVo checker = dao.getChecker(checker_num);
		
		request.setAttribute("checker", checker);
		
		return "/WEB-INF/views/checker/adminCheckerDetail.jsp";
	}
	
}
