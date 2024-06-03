package kr.notice.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.notice.dao.NoticeDao;
import kr.notice.vo.NoticeVo;
import kr.util.FileUtil;

public class DeleteFileAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,String> mapAjax = new HashMap<String,String>();			
		//전송된 데이터 인코딩 타입 지정
		request.setCharacterEncoding("utf-8");
		//전송된 데이터 반환
		int notice_num = Integer.parseInt(request.getParameter("notice_num"));
		
		NoticeDao dao = NoticeDao.getDao();
		NoticeVo db_notice = dao.getNotice(notice_num);
		
		//db_notice에 담긴 notice_photo 가 null 이 아닌 경우에만 실행
		if(db_notice.getNotice_photo() != null) {
			dao.deleteFile(notice_num);
			
			//파일 삭제
			String[] photos = db_notice.getNotice_photo().split(",");
			
			for(String pho : photos) {
				FileUtil.removeFile(request, pho);
			}
		}
		
		mapAjax.put("result", "success");
		
		//JSON 데이터 생성
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";	
	}
	
}
