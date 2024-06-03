package kr.s_re.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.s_re.dao.S_ReDao;
import kr.s_re.vo.S_ReVo;
import kr.sell.dao.SellDao;
import kr.sell.vo.SellVo;
import kr.util.StringUtil;

public class DetailAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int s_re_num = Integer.parseInt(request.getParameter("s_re_num"));
		
		S_ReDao dao = S_ReDao.getDao();
		S_ReVo s_re = dao.getSellReview(s_re_num);
		
		s_re.setS_re_title(StringUtil.useNoHTML(s_re.getS_re_title()));
		s_re.setS_re_content(StringUtil.useNoHTML(s_re.getS_re_content()));
		
		
		 SellDao selldao = SellDao.getDao(); 
		 SellVo sellvo = selldao.getSell(s_re.getSell_num());
		  sellvo.setSell_cname(sellvo.getSell_cname());
		  sellvo.setSell_maker(sellvo.getSell_maker());
		  sellvo.setSell_mile(sellvo.getSell_mile());
		  sellvo.setSell_cnumber(sellvo.getSell_cnumber());
		  sellvo.setSell_place1(sellvo.getSell_place1());
		  sellvo.setSell_place2(sellvo.getSell_place2());
		  sellvo.setSell_check(sellvo.getSell_check());
		request.setAttribute("sellvo", sellvo);
		request.setAttribute("s_re",s_re);
		return "/WEB-INF/views/s_re/detail.jsp";
	}

}
