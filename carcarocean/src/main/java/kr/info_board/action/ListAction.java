package kr.info_board.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.info_board.dao.InfoBoardDao;
import kr.info_board.vo.InfoBoardVo;
import kr.util.PagingUtil;

public class ListAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null) pageNum = "1";
		
		String keyfield = request.getParameter("keyfield");
		String keyword = request.getParameter("keyword");
		
		int boardReport = 0;
		String boardReportParam = request.getParameter("info_board_report");
		if(boardReportParam != null && !boardReportParam.isEmpty()) {
			boardReport = Integer.parseInt(boardReportParam);
		}
		
		InfoBoardDao dao = InfoBoardDao.getDao();
		int count = dao.getInfoCount(keyfield, keyword);
		PagingUtil page = new PagingUtil(keyfield,keyword,Integer.parseInt(pageNum),count,20,10,"list.do");
		
		List<InfoBoardVo> list = null;
		if(count > 0) {
			list = dao.getListInfo(page.getStartRow(), page.getEndRow(), keyfield, keyword);
		}
		request.setAttribute("count", count);
		request.setAttribute("list", list);
		request.setAttribute("page", page.getPage());
		request.setAttribute("info_board_report", boardReport);
		return "/WEB-INF/views/info_board/list.jsp";
	}

}
