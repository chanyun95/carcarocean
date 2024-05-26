package kr.checker.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.checker.dao.CheckerDao;
import kr.checker.vo.CheckerVo;
import kr.controller.Action;

public class AdminCheckerListAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		CheckerDao dao = CheckerDao.getDao();
		
		List<CheckerVo> list = dao.getCheckerAllList();
		
		request.setAttribute("list", list);
		
		return "/WEB-INF/views/checker/adminCheckerList.jsp";
	}

}
