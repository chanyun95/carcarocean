package kr.board.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.b_re.vo.B_ReVo;
import kr.board.dao.BoardDao;
import kr.board.vo.BoardVo;
import kr.controller.Action;
import kr.info_board.dao.InfoBoardDao;
import kr.info_board.vo.InfoBoardVo;
import kr.member.dao.MemberDao;
import kr.member.vo.MemberVo;
import kr.mypage.dao.MyPageDao;
import kr.s_re.vo.S_ReVo;

public class AdminReportListAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		//int가 아닌 Integer를 쓰는 이유는 int는 null값을 받을 수 없다. Integer는 널값을 받을 수 있기 때문에 아래에 로그인 유무를 체크할 수 있다.
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) { //로그인이 되지 않은 경우 즉, 로그인값이 없는경우
			return "redirect:/member/loginForm.do";
		}
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		if(user_auth != 9 ) {
			return "/WEB-INF/views/common/warningPage.jsp";
		}
		//로그인이 된경우
		String keyfield = request.getParameter("keyfield");
		String keyword = request.getParameter("keyword");
		
		BoardDao dao = BoardDao.getDao();
		List<BoardVo> list = dao.getListBoardMain(1, 10);
		
		InfoBoardDao dao2 = InfoBoardDao.getDao();
		List<InfoBoardVo> list2 = dao2.getListInfo(1, 10, keyfield, keyword);
	
		request.setAttribute("list", list);
		request.setAttribute("list2", list2);
		
		return "/WEB-INF/views/board/adminReportList.jsp";
		
	}

}
