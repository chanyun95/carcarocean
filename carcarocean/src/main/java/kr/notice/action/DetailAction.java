package kr.notice.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.notice.dao.NoticeDao;
import kr.notice.vo.NoticeVo;

public class DetailAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		//글 번호 반환
		int notice_num = Integer.parseInt(request.getParameter("notice_num"));
		
		NoticeDao dao = NoticeDao.getDao();
		NoticeVo notice = dao.getNotice(notice_num);
		
		request.setAttribute("notice", notice);
		request.setAttribute("user_num", user_num);
		
		return "/WEB-INF/views/notice/detail.jsp";
	}

}
