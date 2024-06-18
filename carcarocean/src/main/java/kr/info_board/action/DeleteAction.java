package kr.info_board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.info_board.dao.InfoBoardDao;
import kr.info_board.vo.InfoBoardVo;
import kr.util.FileUtil;

public class DeleteAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		int info_board_num = Integer.parseInt(request.getParameter("info_board_num"));
		InfoBoardDao dao = InfoBoardDao.getDao();
		InfoBoardVo db_board = dao.getInfo(info_board_num);
		
		if(user_num != db_board.getMem_num()) {
			request.setAttribute("notice_msg", "잘못된 접근입니다.");
			request.setAttribute("notice_url", request.getContextPath() + "/main/main.do");
			
			return "/WEB-INF/views/common/alert_view.jsp";
		}
		dao.deleteBoard(info_board_num);
		
		FileUtil.removeFile(request, db_board.getInfo_board_photo());
		
		request.setAttribute("notice_msg", "글 삭제 완료");
		request.setAttribute("notice_url", request.getContextPath() + "/info_board/list.do");
		
		return "/WEB-INF/views/common/alert_view.jsp";
	}

}
