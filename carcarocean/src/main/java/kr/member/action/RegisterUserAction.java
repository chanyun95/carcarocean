package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.MemberDao;
import kr.member.vo.MemberVo;


public class RegisterUserAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//전송된 데이터 인코딩 타입 지정
		request.setCharacterEncoding("utf-8");
		//자바빈(VO)생성
		MemberVo member = new MemberVo();
		
		member.setMem_id(request.getParameter("mem_id"));
		member.setMem_name(request.getParameter("mem_name"));
		member.setMem_passwd(request.getParameter("mem_passwd"));
		member.setMem_birth(request.getParameter("mem_birth"));
		member.setMem_phone(request.getParameter("mem_phone"));
		member.setMem_email(request.getParameter("mem_email"));
		member.setMem_zipcode(request.getParameter("mem_zipcode"));
		member.setMem_address1(request.getParameter("mem_address1"));  
		member.setMem_address2(request.getParameter("mem_address2"));
		
		MemberDao dao = MemberDao.getDao();
		dao.insertMember(member);
		
		//회원 등록 후에 회원 번호 다시 가져오기
        member = dao.getMemberByMemId(member.getMem_id());
		
		
		//로그인 처리
        HttpSession session = request.getSession();
        session.setAttribute("user_num", member.getMem_num());
        session.setAttribute("user_name",member.getMem_name());
        session.setAttribute("user_id", member.getMem_id());
        session.setAttribute("user_auth", member.getMem_auth());
        session.setAttribute("user_photo", member.getMem_photo());
		
		request.setAttribute("notice_msg", "회원 가입이 완료되었습니다.");
		request.setAttribute("notice_url", request.getContextPath()+"/main/main.do");
		return "/WEB-INF/views/common/alert_view.jsp";
	}

}
