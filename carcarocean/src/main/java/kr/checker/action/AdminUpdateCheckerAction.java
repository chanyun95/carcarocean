package kr.checker.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.checker.dao.CheckerDao;
import kr.checker.vo.CheckerVo;
import kr.controller.Action;
import kr.util.FileUtil;

public class AdminUpdateCheckerAction implements Action{

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
