package kr.s_re.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.s_re.dao.S_ReDao;
import kr.s_re.vo.S_ReVo;
import kr.util.StringUtil;

public class UpdateFormAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		
		if(user_num == null) {//로그인이 되지 않는 경우
			return "redirect:/member/loginForm.do";
		}
		
		//로그인이 된 경우
		int s_re_num = Integer.parseInt(request.getParameter("s_re_num"));
		
		S_ReDao dao = S_ReDao.getDao();
		List<S_ReVo> sellList = dao.sellList(user_num);
		
		S_ReDao dao2 = S_ReDao.getDao();
		S_ReVo s_re = dao2.getSellReview(s_re_num);
		if(user_num != s_re.getMem_num()) {
			return "/WEB-INF/views/common/notice.jsp";
		}
		//수정폼의 input 태그에서 큰 따옴표 오류 보정
		s_re.setS_re_title(StringUtil.parseQuot(s_re.getS_re_title()));
		s_re.setS_re_content(StringUtil.parseQuot(s_re.getS_re_content()));
		//로그인O , 로그인한 회원번호와 작성자 회원번호 일치
		request.setAttribute("sellList", sellList);
		request.setAttribute("s_re", s_re);

				
		return "/WEB-INF/views/s_re/updateForm.jsp";
	}

}
