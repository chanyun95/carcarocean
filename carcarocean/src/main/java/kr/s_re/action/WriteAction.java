package kr.s_re.action;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.s_re.dao.S_ReDao;
import kr.s_re.vo.S_ReVo;
import kr.sell.dao.SellDao;
import kr.sell.vo.SellVo;

public class WriteAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) { //로그인이 되지 않는 경우
			return "redirect:/member/loginForm.do";
		}
		//로그인 된 경우
		//전송된 데이터 인코딩 타입 지정
		request.setCharacterEncoding("utf-8");
		
		
		SellDao sellDao = SellDao.getDao();
		SellVo sell = sellDao.getSell(Integer.parseInt(request.getParameter("sell_num")));
		
		//자바빈(VO)를 생성한 후 전송된 데이터 반환
		S_ReVo vo = new S_ReVo();
		vo.setS_re_title(request.getParameter("s_re_title"));
		vo.setSell_num(sell.getSell_num());
		vo.setS_re_content(request.getParameter("s_re_content"));
		vo.setMem_num(user_num); //작성자 회원번호
		vo.setSell_cname(sell.getSell_cname());
		vo.setSell_maker(sell.getSell_maker());
		
		S_ReDao dao = S_ReDao.getDao();
		dao.insertSellReview(vo);
		
		request.setAttribute("notice_msg", "판매 후기글 작성완료");
		request.setAttribute("notice_url", request.getContextPath()+"/s_re/list.do");
		
		return "/WEB-INF/views/common/alert_view.jsp";
	}

}
