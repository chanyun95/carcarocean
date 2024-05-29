package kr.mypage.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.MemberDao;
import kr.member.vo.MemberVo;

public class MyQnAFormAction implements Action {

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
		MemberDao dao = MemberDao.getDao();
		MemberVo member = dao.getMember(user_num);
		
		//관심게시물 정보 넣어야됨.
		
		request.setAttribute("member", member);
		return "/WEB-INF/views/member/myQnAForm.jsp";
	}

}
