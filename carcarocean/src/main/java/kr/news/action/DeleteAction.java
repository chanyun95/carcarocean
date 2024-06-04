package kr.news.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.news.dao.NewsDao;
import kr.news.vo.NewsVo;
import kr.util.FileUtil;

public class DeleteAction implements Action{

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
		NewsVo db_news = dao.detailNews(news_num);

		dao.deleteNews(news_num);
		//사진 삭제
		String[] photos = db_news.getNews_photo().split(",");
		for(String pho : photos) {
			FileUtil.removeFile(request, pho);
		}
		request.setAttribute("notice_msg", "뉴스 삭제 완료");
		request.setAttribute("notice_url", request.getContextPath() + "/news/list.do");
		return "/WEB-INF/views/common/alert_view.jsp";
	}

}
