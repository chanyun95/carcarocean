package kr.notice.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.notice.dao.NoticeDao;
import kr.notice.vo.NoticeVo;

public class ModifyFormAction implements Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) { //로그인이 되지 않은 경우 즉, 로그인값이 없는경우
			return "redirect:/member/loginForm.do";
		}
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		if(user_auth != 9 ) {
			return "/WEB-INF/views/common/warningPage.jsp";
		}
		
		int notice_num = Integer.parseInt(request.getParameter("notice_num"));
	
		NoticeDao dao = NoticeDao.getDao();
		NoticeVo notice = dao.getNotice(notice_num);
		
		request.setAttribute("notice", notice);
		
		return "/WEB-INF/views/notice/modifyForm.jsp";
	}

}
