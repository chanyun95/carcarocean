package kr.b_re.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.b_re.dao.B_ReDao;
import kr.b_re.vo.B_ReVo;
import kr.controller.Action;
import kr.util.FileUtil;

public class ModifyAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		
		request.setCharacterEncoding("utf-8");
		
		B_ReVo b_re = new B_ReVo();
		b_re.setMem_num(user_num);
		b_re.setB_re_num(Integer.parseInt(request.getParameter("b_re_num")));
		b_re.setBuy_num(Integer.parseInt(request.getParameter("buy_num")));
		b_re.setB_re_title(request.getParameter("b_re_title"));
		b_re.setB_re_content(request.getParameter("b_re_content"));
		b_re.setB_re_photo(FileUtil.createFiles(request));
		
		B_ReDao dao = B_ReDao.getDao();
		dao.updateB_Re(b_re);
		System.out.println(request.getParameter("b_re_photo"));
		request.setAttribute("notice_msg", "구매 후기 글 수정 완료");
		request.setAttribute("notice_url", request.getContextPath()+"/b_re/detail.do?b_re_num="+b_re.getB_re_num());
		
		return "/WEB-INF/views/common/alert_view.jsp";
	}

}
