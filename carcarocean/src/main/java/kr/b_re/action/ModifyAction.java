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
		
		int b_re_num = Integer.parseInt(request.getParameter("b_re_num"));
		B_ReDao dao = B_ReDao.getDao();
		B_ReVo db_b_re = dao.getB_Re(b_re_num);
		
		B_ReVo b_re = new B_ReVo();
		b_re.setMem_num(user_num);
		b_re.setB_re_num(b_re_num);
		b_re.setBuy_num(Integer.parseInt(request.getParameter("buy_num")));
		b_re.setB_re_title(request.getParameter("b_re_title"));
		b_re.setB_re_content(request.getParameter("b_re_content"));
		b_re.setB_re_photo(FileUtil.createFiles(request));
		
		dao.updateB_Re(b_re);
		
		//새 파일로 업로드 할 경우 기존 파일 삭제
		if(b_re.getB_re_photo()!=null && !"".equals(b_re.getB_re_photo())) {
			FileUtil.removeFile(request, db_b_re.getB_re_photo());
		}
		
		request.setAttribute("notice_msg", "구매 후기 글 수정 완료");
		request.setAttribute("notice_url", request.getContextPath()+"/b_re/detail.do?b_re_num="+b_re.getB_re_num());
		
		return "/WEB-INF/views/common/alert_view.jsp";
	}

}
