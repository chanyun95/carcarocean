package kr.event.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.event.dao.EventDao;
import kr.event.vo.EventVo;
import kr.util.FileUtil;

public class UpdateAction implements Action{

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
		//전송된 데이터 반환
		int event_num = Integer.parseInt(request.getParameter("event_num"));
		
		EventDao dao = EventDao.getDao();
		//수정전 데이터
		EventVo db_event = dao.detailEvent(event_num);
		
		EventVo event = new EventVo();
		event.setEvent_num(event_num);
		event.setEvent_title(request.getParameter("event_title"));
		event.setEvent_content(request.getParameter("event_content"));
		event.setEvent_photo(FileUtil.createFiles(request));
		dao.updateEvent(event);
		
		if(event.getEvent_photo() != null && !"".equals(event.getEvent_photo())) {
			//새 파일로 교체할 때 원래 파일 제거
			FileUtil.removeFile(request, db_event.getEvent_photo());
		}
		return "redirect:/event/detail.do?event_num=" + event_num;
	}

}
