package kr.event.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.event.dao.EventDao;
import kr.event.vo.EventVo;
import kr.util.StringUtil;


public class DetailAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//글번호 반환
				int event_num = Integer.parseInt(request.getParameter("event_num"));
				EventDao dao = EventDao.getDao();
				
				EventVo event = dao.detailEvent(event_num);
				
				//HTML를 허용하지 않음
				event.setEvent_title(StringUtil.useNoHTML(event.getEvent_title()));
				//HTML를 허용하지 않음
				event.setEvent_content(StringUtil.useBrNoHTML(event.getEvent_content()));
				
				request.setAttribute("event", event);
		return "/WEB-INF/views/event/detail.jsp";
	}

}
