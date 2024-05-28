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
            // 게시물이 존재하지 않는 경우 처리
            return "/WEB-INF/views/common/error.jsp"; // 또는 적절한 오류 페이지로 리다이렉트
        }

        if (user_num != board.getMem_num()) {
            // 로그인된 사용자와 게시물 작성자가 다른 경우 처리
            return "/WEB-INF/views/common/notice.jsp"; // 또는 적절한 안내 페이지로 리다이렉트
        }

        // 수정 폼으로 이동
        board.setBoard_title(StringUtil.parseQuot(board.getBoard_title()));
        request.setAttribute("board", board);
        return "/WEB-INF/views/board/updateForm.jsp";
    }
}
