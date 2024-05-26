package kr.notice.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.notice.dao.NoticeDao;
import kr.notice.vo.NoticeVo;
import kr.util.FileUtil;

public class ModifyAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		
		NoticeVo notice = new NoticeVo();
		notice.setNotice_num(Integer.parseInt(request.getParameter("notice_num")));
		notice.setNotice_title(request.getParameter("notice_title"));
		notice.setNotice_content(request.getParameter("notice_content"));
		notice.setNotice_photo(FileUtil.createFile(request, "notice_photo"));
		String noticeImptParam = request.getParameter("notice_impt");
	    int noticeImpt = 0; // 기본값 설정
	    
	    if (noticeImptParam != null && !noticeImptParam.isEmpty()) {
	        noticeImpt = Integer.parseInt(noticeImptParam);
	    }
	    notice.setNotice_impt(noticeImpt);
		
		NoticeDao dao = NoticeDao.getDao();
		dao.updateNotice(notice);
		
		request.setAttribute("notice_msg", "공지사항 글 수정 완료");
		request.setAttribute("notice_url", request.getContextPath()+"/notice/detail.do?notice_num="+notice.getNotice_num());
		
		return "/WEB-INF/views/common/alert_view.jsp";
		
	}

}
