package kr.news.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.news.dao.NewsDao;
import kr.news.vo.NewsVo;
import kr.util.StringUtil;

public class UpdateFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) {//로그인 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		//로그인 된 경우
		int news_num = Integer.parseInt(request.getParameter("news_num"));
		NewsDao dao = NewsDao.getDao();
		NewsVo news = dao.detailNews(news_num);
		
		//큰 따옴표 처리 
		//수정폼의 input 태그에서 큰 따옴표 오류 보정
		news.setNews_title(StringUtil.parseQuot(news.getNews_title()));
		
		//로그인이 되어있고 로그인한 회원번호와 작성자 회원번호 일치
		request.setAttribute("news", news);
		
		return "/WEB-INF/views/news/updateForm.jsp";
	}

}
