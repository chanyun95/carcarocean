package kr.notice.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.notice.dao.NoticeDao;
import kr.notice.vo.NoticeVo;

public class ModifyFormAction implements Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int notice_num = Integer.parseInt(request.getParameter("notice_num"));
	
		NoticeDao dao = NoticeDao.getDao();
		NoticeVo notice = dao.getNotice(notice_num);
		
		request.setAttribute("notice", notice);
		
		return "/WEB-INF/views/notice/modifyForm.jsp";
	}

}
