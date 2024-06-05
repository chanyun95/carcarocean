package kr.main.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.b_re.dao.B_ReDao;
import kr.b_re.vo.B_ReVo;
import kr.board.dao.BoardDao;
import kr.board.vo.BoardVo;
import kr.controller.Action;
import kr.event.dao.EventDao;
import kr.event.vo.EventVo;
import kr.news.dao.NewsDao;
import kr.news.vo.NewsVo;
import kr.notice.dao.NoticeDao;
import kr.notice.vo.NoticeVo;
import kr.s_re.dao.S_ReDao;
import kr.s_re.vo.S_ReVo;
import kr.util.PagingUtil;

public class MainAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("utf-8");
		
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null) pageNum = "1";
		
		
		
		 NoticeDao noticeDao = NoticeDao.getDao();
		 List<NoticeVo> noticeList = noticeDao.getListNoticeMain(1,4); // 최근 10개의 공지사항을 가져옴
		
		 EventDao Eventdao = EventDao.getDao();
		 List<EventVo> EventList = Eventdao.getListEventMain(1,4);
		
		 BoardDao Boarddao = BoardDao.getDao(); 
		 List<BoardVo> BoardList = Boarddao.getListBoardMain(1,4);
		 
		 NewsDao Newsdao = NewsDao.getDao();
		 List<NewsVo> NewsList = Newsdao.getListNewsMain(1,4);
		 
		//페이지 처리(판매후기)
		 S_ReDao S_redao = S_ReDao.getDao();
		 List<S_ReVo> S_relist = S_redao.getListSellReviewMain(1,9);
		
		//페이지 처리(구매후기)
		B_ReDao dao = B_ReDao.getDao();
		int count = dao.getB_reCount();
		
		PagingUtil page = new PagingUtil(Integer.parseInt(pageNum), count, 10, 1, "list.do");
		List<B_ReVo> list = null;
		if(count > 0) {
			list = dao.getListB_re(page.getStartRow(), page.getEndRow());
		}
		
		
		
		//공지사항 저장
		request.setAttribute("noticeList", noticeList);
		//이벤트 저장
		request.setAttribute("EventList", EventList);
		//자유게시판 저장
		request.setAttribute("BoardList", BoardList);
		//뉴스 저장
		request.setAttribute("NewsList", NewsList);
		//JSP 경로 반환
		
		//구매후기 저장
		request.setAttribute("count", count);
		request.setAttribute("list", list);
		request.setAttribute("page", page.getPage());
		
		//판매후기 저장
		request.setAttribute("S_relist", S_relist);
				
				
		return "/WEB-INF/views/main/main.jsp";
	}
}