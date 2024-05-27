package kr.checker.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.checker.dao.CheckerDao;
import kr.checker.vo.CheckerVo;
import kr.controller.Action;
import kr.util.FileUtil;

public class AdminUpdateCheckerAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		CheckerVo checker = new CheckerVo();
		CheckerDao dao = CheckerDao.getDao();
		checker.setChecker_num(Integer.parseInt(request.getParameter("checker_num")));
		checker.setChecker_name(request.getParameter("checker_name"));
		checker.setChecker_company(request.getParameter("checker_company"));
		checker.setChecker_phone(request.getParameter("checker_phone"));
		FileUtil.removeFile(request, checker.getChecker_photo());
		checker.setChecker_photo(FileUtil.createFile(request, "checker_photo"));
		
		dao.updateChecker(checker);
		return "redirect:/checker/adminCheckerDetail.do?checker_num="+checker.getChecker_num();
	} 
	
}
