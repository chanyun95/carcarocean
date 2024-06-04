package kr.news.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.news.dao.NewsDao;
import kr.news.vo.NewsVo;
import kr.util.FileUtil;

public class UpdateAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) {//로그인 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		//로그인 된 경우
		//전송된 데이터 인코딩 타입 지정
		request.setCharacterEncoding("utf-8");
		//전송된 데이터 반환
		int news_num = Integer.parseInt(request.getParameter("news_num"));
		
		NewsDao dao = NewsDao.getDao();
		//수정전 데이터
		NewsVo db_news = dao.detailNews(news_num);
		
		NewsVo news = new NewsVo();
		news.setNews_num(news_num);
		news.setNews_title(request.getParameter("news_title"));
		news.setNews_content(request.getParameter("news_content"));
		news.setNews_photo(FileUtil.createFiles(request));
		dao.updateNews(news);
		
		if(news.getNews_photo() != null && !"".equals(news.getNews_photo())) {
			//새 파일로 교체할 때 원래 파일 제거
			FileUtil.removeFile(request, db_news.getNews_photo());
		}
		return "redirect:/news/detail.do?news_num=" + news_num;
	}

}
