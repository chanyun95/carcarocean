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
		int notice_num = Integer.parseInt(request.getParameter("notice_num"));
		NoticeDao dao = NoticeDao.getDao();
		NoticeVo db_notice = dao.getNotice(notice_num);
		
		NoticeVo notice = new NoticeVo();
		notice.setNotice_num(Integer.parseInt(request.getParameter("notice_num")));
		notice.setNotice_title(request.getParameter("notice_title"));
		notice.setNotice_content(request.getParameter("notice_content"));
		notice.setNotice_modify(request.getParameter("notice_modify"));
		notice.setNotice_photo(FileUtil.createFiles(request));
		
		//중요도 체크
		String noticeImptParam = request.getParameter("notice_impt");
	    int noticeImpt = 0; // 기본값 설정
	    
	    if (noticeImptParam != null && !noticeImptParam.isEmpty()) {
	        noticeImpt = Integer.parseInt(noticeImptParam);
	    }
	    notice.setNotice_impt(noticeImpt);
	    
		dao.updateNotice(notice);
		
		//새 파일로 업로드 할 경우 기존 파일 삭제
		if(notice.getNotice_photo()!=null && !"".equals(notice.getNotice_photo())) {
			FileUtil.removeFile(request, db_notice.getNotice_photo());
		}
		
		request.setAttribute("notice_msg", "공지사항 글 수정 완료");
		request.setAttribute("notice_url", request.getContextPath()+"/notice/detail.do?notice_num="+notice.getNotice_num());
		
		return "/WEB-INF/views/common/alert_view.jsp";
		
	}

}
