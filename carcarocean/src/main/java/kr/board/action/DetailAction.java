package kr.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import kr.board.dao.BoardDao;
import kr.board.vo.BoardVo;
import kr.controller.Action;
import kr.util.StringUtil;

public class DetailAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int board_num = Integer.parseInt(request.getParameter("board_num"));

		BoardDao dao = BoardDao.getDao();
		
		BoardVo board = dao.getBoard(board_num);
		
		dao.updateReadCount(board_num);
		
		board.setBoard_title(StringUtil.useNoHTML(board.getBoard_title()));
		board.setBoard_content(StringUtil.useBrNoHTML(board.getBoard_content()));
		
		int count = dao.checkReportCount(board_num);
		request.setAttribute("check_redirect",count);
		request.setAttribute("board", board);
		return "/WEB-INF/views/board/detail.jsp";
	}

}
