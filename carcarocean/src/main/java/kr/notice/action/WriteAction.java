package kr.notice.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.notice.dao.NoticeDao;
import kr.notice.vo.NoticeVo;
import kr.util.FileUtil;

public class WriteAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//로그인O
		request.setCharacterEncoding("utf-8");
		//전송된 데이터 반환
		NoticeVo notice = new NoticeVo();
		notice.setNotice_title(request.getParameter("notice_title"));
		notice.setNotice_content(request.getParameter("notice_content"));
		notice.setNotice_photo(FileUtil.createFile(request, "notice_photo"));
		notice.setNotice_impt(Integer.parseInt(request.getParameter("notice_impt")));
		
		NoticeDao dao = NoticeDao.getDao();
		dao.insertNotice(notice);
		
		request.setAttribute("notice_msg", "공지사항 글 쓰기 완료");
		request.setAttribute("notice_url", request.getContextPath()+"/notice/writeForm.do");
		
		return "/WEB-INF/views/common/alert_view.jsp";
	}

}
