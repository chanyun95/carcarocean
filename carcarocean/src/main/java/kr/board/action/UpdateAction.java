package kr.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.board.dao.BoardDao;
import kr.board.vo.BoardVo;
import kr.controller.Action;
import kr.util.FileUtil;
  
public class UpdateAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = 
				(Integer)session.getAttribute("user_num");
		if(user_num == null) {//로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		request.setCharacterEncoding("utf-8");
		
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		
		BoardDao dao = BoardDao.getDao();
		BoardVo db_board = dao.getBoard(board_num);
		
		if(user_num != db_board.getMem_num()) {
			request.setAttribute("notice_msg", "잘못된 접근입니다.");
			request.setAttribute("notice_url", request.getContextPath() + "/main/main.do");
			
			return "/WEB-INF/views/common/alert_view.jsp";
		}
		BoardVo board = new BoardVo();
		board.setBoard_num(board_num);
		board.setBoard_title(request.getParameter("board_title"));
		board.setBoard_content(request.getParameter("board_content"));
		board.setBoard_Photo(FileUtil.createFile(request, "board_photo"));
		
		dao.updateBoard(board);
		
		if(board.getBoard_photo() != null && !"".equals(board.getBoard_photo())) {
			FileUtil.removeFile(request, db_board.getBoard_photo());
		}
		return "redirect:/board/detail.do?board_num="+board_num;
	}

}
