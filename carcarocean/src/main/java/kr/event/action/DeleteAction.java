package kr.event.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.event.dao.EventDao;
import kr.event.vo.EventVo;
import kr.util.FileUtil;

public class DeleteAction implements Action{

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
		EventVo db_event = dao.detailEvent(event_num);
		
		dao.deleteEvent(event_num);
		//파일 삭제
		FileUtil.removeFile(request, db_event.getEvent_photo());
		request.setAttribute("notice_msg", "글 삭제 완료");
		request.setAttribute("notice_url", request.getContextPath() + "/event/list.do");
		return "/WEB-INF/views/common/alert_view.jsp";
	}

}
