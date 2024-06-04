package kr.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.board.dao.BoardDao;
import kr.board.vo.BoardVo;
import kr.controller.Action;
import kr.util.StringUtil;

public class UpdateFormAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Integer user_num = (Integer) session.getAttribute("user_num");
        if (user_num == null) {
            // 로그인되지 않은 경우
            return "redirect:/member/loginForm.do";
        }

        int board_num = Integer.parseInt(request.getParameter("board_num"));
        BoardDao dao = BoardDao.getDao();
        BoardVo board = dao.getBoard(board_num);

        if (board == null) {
        	request.setAttribute("notice_msg", "게시물이 존재하지 않습니다.");
			request.setAttribute("notice_url", request.getContextPath() + "/main/main.do");
			
			return "/WEB-INF/views/common/alert_view.jsp";
        }

        if (user_num != board.getMem_num()) {
        	request.setAttribute("notice_msg", "잘못된 접근입니다.");
			request.setAttribute("notice_url", request.getContextPath() + "/main/main.do");
			
			return "/WEB-INF/views/common/alert_view.jsp";
        }

        // 수정 폼으로 이동
        board.setBoard_title(StringUtil.parseQuot(board.getBoard_title()));
        request.setAttribute("board", board);
        return "/WEB-INF/views/board/updateForm.jsp";
    }
}
