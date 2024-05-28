package kr.event.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.event.dao.EventDao;
import kr.event.vo.EventVo;
import kr.util.FileUtil;

public class WriteAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) {//로그인 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		//로그인 된 경우
		//전송된 데이터 인코딩 타입 지정
		request.setCharacterEncoding("utf-8");
		//자바빈(VO)를 생성한 후 전송된 데이터를 저장
		EventVo event = new EventVo();
		event.setEvent_title(request.getParameter("event_title"));
		event.setEvent_content(request.getParameter("event_content"));
		event.setEvent_photo(FileUtil.createFile(request, "event_photo"));
		
		EventDao dao = EventDao.getDao();
		dao.insertEvent(event);
		
		request.setAttribute("notice_msg", "글쓰기 완료");
		request.setAttribute("notice_url", request.getContextPath() + "/event/list.do");
		//JSP 경로 반환
		return "/WEB-INF/views/common/alert_view.jsp";
	}

}
