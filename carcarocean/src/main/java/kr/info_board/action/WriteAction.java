package kr.info_board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.info_board.dao.InfoBoardDao;
import kr.info_board.vo.InfoBoardVo;

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
		
		InfoBoardVo info = new InfoBoardVo();
		info.setInfo_board_title(request.getParameter("info_board_title"));
		info.setInfo_board_content(request.getParameter("info_board_content"));
		info.setInfo_board_photo(request.getParameter("info_board_photo"));
		info.setMem_num(user_num);
		
		InfoBoardDao dao = InfoBoardDao.getDao();
		dao.insertInfo(info);
		
		request.setAttribute("notice_msg", "글쓰기 완료");
		request.setAttribute("notice_url", request.getContextPath() + "/info_board/list.do");
		
		return "/WEB-INF/views/common/alert_view.jsp";
	}

}
