package kr.checker.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.checker.dao.CheckerDao;
import kr.checker.vo.CheckerVo;
import kr.controller.Action;
import kr.util.FileUtil;

public class AdminDeleteCheckerAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int checker_num = Integer.parseInt(request.getParameter("checker_num"));
		
		CheckerDao dao = CheckerDao.getDao();
		CheckerVo checker = dao.getChecker(checker_num);
		dao.deleteChecker(checker_num);
		FileUtil.removeFile(request, checker.getChecker_photo());
		
		return "redirect:/checker/adminCheckerList.do";
	}
	
}
 