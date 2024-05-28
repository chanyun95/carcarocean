package kr.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.board.dao.BoardDao;
import kr.board.vo.BoardVo;
import kr.controller.Action;
import kr.util.FileUtil;

public class WriteAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) { //로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		//로그인 된 경우
		request.setCharacterEncoding("utf-8");
		
		BoardVo board = new BoardVo();
		board.setBoard_title(request.getParameter("board_title"));
		board.setBoard_content(request.getParameter("board_content"));
		board.setBoard_Photo(FileUtil.createFile(request, "board_photo"));
		board.setMem_num(user_num);
		
		BoardDao dao = BoardDao.getDao();
		dao.insertBoard(board);
		
		request.setAttribute("notice_msg", "글쓰기 완료");
		request.setAttribute("notice_url", request.getContextPath() + "/board/list.do");
		
		return "/WEB-INF/views/common/alert_view.jsp";
	}

}
