package kr.checker.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.car.dao.CarDao;
import kr.checker.dao.CheckerDao;
import kr.checker.vo.CheckerVo;
import kr.controller.Action;

public class AdminCheckerDetailAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//로그인 체크&관리자 체크
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		//로그인 안되어 있을 시 로그인 폼으로 이동
		if(user_num==null) {
			return "redirect:/member/loginForm.do";
		}
		//관리자아닌데 잘못된 접근시 main.do로 이동
		if(user_auth!=9) {
			return "/WEB-INF/views/common/warningPage.jsp";
		}
		
		int checker_num = Integer.parseInt(request.getParameter("checker_num"));
		CheckerDao dao = CheckerDao.getDao();
		CheckerVo checker = dao.getChecker(checker_num);
		
		request.setAttribute("checker", checker);
		
		// 해당 검수자가 검수한 차량이 존재한다면 삭제 버튼을 가리기 위해 검수한 차량의 갯수 보냄
		CarDao carDao = CarDao.getDao();
		int count = carDao.getCarCountByChecker(checker_num);
		request.setAttribute("count", count);
		
		return "/WEB-INF/views/checker/adminCheckerDetail.jsp";
	}
	
}
