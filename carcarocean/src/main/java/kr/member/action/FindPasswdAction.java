package kr.member.action;


import java.lang.ProcessBuilder.Redirect;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.MemberDao;
import kr.member.vo.MemberVo;

public class FindPasswdAction implements Action{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        request.setCharacterEncoding("utf-8");
        // 전송된 데이터 반환
        String mem_name = request.getParameter("mem_name");
        String mem_email = request.getParameter("mem_email");
        String mem_id = request.getParameter("mem_id");
        String mem_phone = request.getParameter("mem_phone");
        
        MemberDao dao = MemberDao.getDao();
        // 매개변수로 실제 사용자가 입력한 값 전달
        MemberVo member = dao.findPasswd(mem_name, mem_email, mem_id, mem_phone);
        if (member == null || member.getMem_passwd() == null) {
            request.setAttribute("notice_msg", "입력된 정보가 일치하지 않습니다.");
        } else {
        request.setAttribute("mem_passwd", member.getMem_passwd());
        }
        return "/WEB-INF/views/member/findPasswd.jsp";
    }
}
