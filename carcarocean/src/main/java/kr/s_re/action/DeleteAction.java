package kr.s_re.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.s_re.dao.S_ReDao;
import kr.s_re.vo.S_ReVo;

public class DeleteAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();  
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) {
			return "redirect:/member/login.do";
		}
		//로그인O
		request.setCharacterEncoding("utf-8");
				
		int s_re_num = Integer.parseInt(request.getParameter("s_re_num"));
		S_ReDao dao = S_ReDao.getDao();
		S_ReVo s_re = dao.getSellReview(s_re_num);
		if(user_num != s_re.getMem_num()) {
			return "/WEB-INF/views/common/notice.jsp";
		}
		dao.deleteSellReview(s_re_num);
		
		request.setAttribute("notice_msg", "글 삭제 완료");
		request.setAttribute("notice_url", request.getContextPath()+"/s_re/list.do");
		return "/WEB-INF/views/common/alert_view.jsp";
	}

}
