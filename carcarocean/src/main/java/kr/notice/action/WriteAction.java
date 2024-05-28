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
		
		//전송된 데이터 인코딩 타입 지정
		request.setCharacterEncoding("utf-8");
		//전송된 데이터 반환
		NoticeVo notice = new NoticeVo();
		notice.setNotice_title(request.getParameter("notice_title"));
		notice.setNotice_content(request.getParameter("notice_content"));
		notice.setNotice_photo(FileUtil.createFiles(request));
		
		//중요도 체크
		String noticeImptParam = request.getParameter("notice_impt");
	    int noticeImpt = 0; // 기본값 설정
	    
	    if (noticeImptParam != null && !noticeImptParam.isEmpty()) {
	        noticeImpt = Integer.parseInt(noticeImptParam);
	    }
	    notice.setNotice_impt(noticeImpt);
		
		NoticeDao dao = NoticeDao.getDao();
		dao.insertNotice(notice);
		
		request.setAttribute("notice_msg", "공지사항 글 쓰기 완료");
		request.setAttribute("notice_url", request.getContextPath()+"/notice/list.do");
		
		return "/WEB-INF/views/common/alert_view.jsp";
	}

}
