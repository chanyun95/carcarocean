package kr.info_board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.info_board.dao.InfoBoardDao;
import kr.info_board.vo.InfoBoardVo;
import kr.util.StringUtil;

public class UpdateFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if (user_num == null) {
            // 로그인되지 않은 경우
            return "redirect:/member/loginForm.do";
        }
		//로그인 된 경우
		int info_board_num = Integer.parseInt(request.getParameter("info_board_num"));
		InfoBoardDao dao = InfoBoardDao.getDao();
		InfoBoardVo info = dao.getInfo(info_board_num);
		if (info == null) {
			request.setAttribute("notice_msg", "게시물이 존재하지 않습니다.");
			request.setAttribute("notice_url", request.getContextPath() + "/main/main.do");
			
			return "/WEB-INF/views/common/alert_view.jsp";
        }

        if (user_num != info.getMem_num()) {
        	request.setAttribute("notice_msg", "잘못된 접근입니다.");
			request.setAttribute("notice_url", request.getContextPath() + "/main/main.do");
			
			return "/WEB-INF/views/common/alert_view.jsp";
        }

        // 수정 폼으로 이동
        info.setInfo_board_title(StringUtil.parseQuot(info.getInfo_board_title()));
        request.setAttribute("info",	info);
        return "/WEB-INF/views/info_board/updateForm.jsp";
	}

}
