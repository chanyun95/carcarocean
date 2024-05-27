package kr.s_re.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.checker.dao.CheckerDao;
import kr.checker.vo.CheckerVo;
import kr.controller.Action;
import kr.s_re.vo.S_ReVo;

public class S_re_WriteAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) { //로그인이 되지 않는 경우
			return "redirect:/member/loginForm.do";
		}
		//로그인 된 경우
		//전송된 데이터 인코딩 타입 지정
		request.setCharacterEncoding("utf-8");
		//자바빈(VO)를 생성한 후 전송된 데이터 반환
		S_ReVo vo = new S_ReVo();
		vo.setS_re_title(request.getParameter("s_re_title"));
		vo.setS_re_content(request.getParameter("s_re_content"));
		
		//검수자 정보
		CheckerDao checkerDao = CheckerDao.getDao();
		List<CheckerVo> checkerList = checkerDao.getCheckerAllList();
		return "/WEB-INF/views/common/alert_view.jsp";
	}

}
