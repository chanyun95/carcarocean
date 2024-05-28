package kr.event.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.event.dao.EventDao;
import kr.event.vo.EventVo;
import kr.util.StringUtil;

public class UpdateFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) {//로그인 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		//로그인 된 경우
		int event_num = Integer.parseInt(request.getParameter("event_num"));
		EventDao dao = EventDao.getDao();
		EventVo event = dao.detailEvent(event_num);
		
		//큰 따옴표 처리 
		//수정폼의 input 태그에서 큰 따옴표 오류 보정
		event.setEvent_title(StringUtil.parseQuot(event.getEvent_title()));
		
		//로그인이 되어있고 로그인한 회원번호와 작성자 회원번호 일치
		request.setAttribute("event", event);
		
		return "/WEB-INF/views/event/updateForm.jsp";
	}

}
