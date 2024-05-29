package kr.s_re.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.car.vo.CarVO;
import kr.checker.dao.CheckerDao;
import kr.checker.vo.CheckerVo;
import kr.controller.Action;
import kr.s_re.dao.S_ReDao;
import kr.s_re.vo.S_ReVo;

public class WriteFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		
		if(user_num == null) {//로그인이 되지 않는 경우
			return "redirect:/member/loginForm.do";
		}
		//로그인 된 경우
		
		//검수자 정보
		//CheckerDao checkerDao = CheckerDao.getDao();
		//List<CheckerVo> checkerList = checkerDao.getCheckerAllList();
		
		
		S_ReDao dao = S_ReDao.getDao();
		List<S_ReVo> sellList = dao.sellList(user_num);
		
		
		request.setAttribute("sellList", sellList);
		
		return "/WEB-INF/views/s_re/writeForm.jsp";
	}

}
