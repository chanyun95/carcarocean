package kr.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.board.dao.BoardDao;
import kr.board.vo.BoardVo;
import kr.controller.Action;
import kr.util.FileUtil;

public class DeleteAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		if(user_auth != 9) {
			return "/WEB-INF/views/common/warningPage.jsp";
		}
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		
		BoardDao dao = BoardDao.getDao();
		BoardVo db_board = dao.getBoard(board_num);
		
		if(user_num != db_board.getMem_num()) {
			request.setAttribute("notice_msg", "잘못된 접근입니다.");
			request.setAttribute("notice_url", request.getContextPath() + "/main/main.do");
			
			return "/WEB-INF/views/common/alert_view.jsp";
		}
		dao.deleteBoard(board_num);
		
		FileUtil.removeFile(request, db_board.getBoard_photo());
		
		request.setAttribute("notice_msg", "글 삭제 완료");
		request.setAttribute("notice_url", request.getContextPath() + "/board/list.do");
		
		return "/WEB-INF/views/common/alert_view.jsp";
	}

}
