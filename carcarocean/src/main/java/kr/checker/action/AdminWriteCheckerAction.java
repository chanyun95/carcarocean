package kr.checker.action;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.checker.dao.CheckerDao;
import kr.checker.vo.CheckerVo;
import kr.controller.Action;
import kr.util.FileUtil;

public class AdminWriteCheckerAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		request.setCharacterEncoding("utf-8");
		
		CheckerVo checker = new CheckerVo();
		checker.setChecker_name(request.getParameter("checker_name"));
		checker.setChecker_company(request.getParameter("checker_company"));
		checker.setChecker_phone(request.getParameter("checker_phone"));
		
		checker.setChecker_photo(FileUtil.createFiles(request));
		
		CheckerDao dao = CheckerDao.getDao();
		dao.insertChecker(checker);
		
		return "redirect:/checker/adminCheckerList.do";
	}

}
