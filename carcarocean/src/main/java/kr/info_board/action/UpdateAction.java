package kr.info_board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.info_board.dao.InfoBoardDao;
import kr.info_board.vo.InfoBoardVo;
import kr.util.FileUtil;

public class UpdateAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) { //로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		//로그인 된 경우
		request.setCharacterEncoding("utf-8");
		
		int info_board_num = Integer.parseInt(request.getParameter("info_board_num"));
		
		InfoBoardDao dao = InfoBoardDao.getDao();
		InfoBoardVo db_info = dao.getInfo(info_board_num);
		
		if(user_num != db_info.getMem_num()) {
			request.setAttribute("notice_msg", "잘못된 접근입니다.");
			request.setAttribute("notice_url", request.getContextPath() + "/main/main.do");
			
			return "/WEB-INF/views/common/alert_view.jsp";
		}
		InfoBoardVo info = new InfoBoardVo();
		info.setInfo_board_num(info_board_num);
		info.setInfo_board_title(request.getParameter("info_board_title"));
		info.setInfo_board_content(request.getParameter("info_board_content"));
		info.setInfo_board_photo(FileUtil.createFile(request, "info_board_photo"));
		
		dao.updateInfo(info);
		
		if(info.getInfo_board_photo() != null && !"".equals(info.getInfo_board_photo())) {
			FileUtil.removeFile(request, db_info.getInfo_board_photo());
		}
		return "redirect:/info_board/detail.do?info_board_num=" + info_board_num;
	}

}
