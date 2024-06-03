package kr.info_board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.info_board.dao.InfoBoardDao;
import kr.info_board.vo.InfoBoardVo;
import kr.util.StringUtil;

public class DetailAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int info_board_num = Integer.parseInt(request.getParameter("info_board_num"));
		
		InfoBoardDao dao = InfoBoardDao.getDao();
		
		InfoBoardVo info = dao.getInfo(info_board_num);
		
		dao.updateReadCount(info_board_num);
		
		info.setInfo_board_title(StringUtil.useNoHTML(info.getInfo_board_title()));
		info.setInfo_board_content(StringUtil.useBrNoHTML(info.getInfo_board_content()));
		
		/* int count = dao.checkReportCount(info_board_num); */
		/* request.setAttribute("check_redirect", count); */
		request.setAttribute("info", info);
		return "/WEB-INF/views/info_board/detail.jsp";
	}

}
