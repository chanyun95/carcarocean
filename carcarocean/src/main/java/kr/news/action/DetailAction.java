package kr.news.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.news.dao.NewsDao;
import kr.news.vo.NewsVo;
import kr.util.StringUtil;

public class DetailAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//글번호 반환
		int news_num = Integer.parseInt(request.getParameter("news_num"));
		NewsDao dao = NewsDao.getDao();
		dao.updateReadCount(news_num);
		NewsVo news = dao.detailNews(news_num);
		
		//HTML를 허용하지 않음
		news.setNews_title(StringUtil.useNoHTML(news.getNews_title()));
		//HTML를 허용하지 않음
		news.setNews_content(StringUtil.useBrNoHTML(news.getNews_content()));
		
		request.setAttribute("news", news);
		return "/WEB-INF/views/news/detail.jsp";
	}

}
