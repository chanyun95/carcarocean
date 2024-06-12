package kr.mypage.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.b_re.vo.B_ReVo;
import kr.board.vo.BoardVo;
import kr.controller.Action;
import kr.member.dao.MemberDao;
import kr.member.vo.MemberVo;
import kr.mypage.dao.MyPageDao;
import kr.s_re.vo.S_ReVo;
import kr.sell.vo.SellVo;

public class MyWriteBoardFormAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		//int가 아닌 Integer를 쓰는 이유는 int는 null값을 받을 수 없다. Integer는 널값을 받을 수 있기 때문에 아래에 로그인 유무를 체크할 수 있다.
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) { //로그인이 되지 않은 경우 즉, 로그인값이 없는경우
			return "redirect:/member/loginForm.do";
		}
		
		//로그인이 된경우
		//회원정보
		//회원정보
		MemberDao dao1 = MemberDao.getDao();
		
		MemberVo member = dao1.getMember(user_num);
		MyPageDao dao = MyPageDao.getDao();
		//리스트 정보

		List<BoardVo> list = MyPageDao.MyWrite(user_num);
		List<B_ReVo> blist = MyPageDao.MyB_Re(user_num);
		List<S_ReVo> slist = MyPageDao.MyS_Re(user_num);
		request.setAttribute("list", list);
		request.setAttribute("blist", blist);
		request.setAttribute("slist", slist);
		request.setAttribute("member", member);
		return "/WEB-INF/views/member/myWriteBoardForm.jsp";
	}

}
