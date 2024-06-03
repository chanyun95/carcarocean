package kr.news.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.news.dao.NewsDao;
import kr.news.vo.NewsVo;
import kr.util.FileUtil;

public class WriteAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//전송된 데이터 인코딩 타입 지정
				request.setCharacterEncoding("utf-8");
				//자바빈(VO)를 생성한 후 전송된 데이터를 저장
				NewsVo news = new NewsVo();
				news.setNews_title(request.getParameter("news_title"));
				news.setNews_content(request.getParameter("news_content"));
				news.setNews_photo(FileUtil.createFiles(request));
				
				NewsDao dao = NewsDao.getDao();
				dao.insertNews(news);
				
				request.setAttribute("notice_msg", "뉴스 등록 완료");
				request.setAttribute("notice_url", request.getContextPath() + "/news/list.do");
				//JSP 경로 반환
				return "/WEB-INF/views/common/alert_view.jsp";
	}

}
