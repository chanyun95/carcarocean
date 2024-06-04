package kr.news.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.news.dao.NewsDao;
import kr.news.vo.NewsVo;
import kr.util.FileUtil;

public class DeletePhotoAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> mapAjax = new HashMap<String, String>();
		//전송된 데이터 인코딩 타입 지정
		request.setCharacterEncoding("utf-8");
		//전송된 데이터 반환
		int news_num = Integer.parseInt(request.getParameter("news_num"));
		NewsDao dao = NewsDao.getDao();
		NewsVo db_news = dao.detailNews(news_num);
		dao.deletePhoto(news_num);
		//사진 삭제
		FileUtil.removeFile(request, db_news.getNews_photo());
		mapAjax.put("result", "success");
		
		//JSON 데이터 생성
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		request.setAttribute("ajaxData", ajaxData);
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
