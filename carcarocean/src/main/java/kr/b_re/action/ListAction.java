package kr.b_re.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.b_re.dao.B_ReDao;
import kr.b_re.vo.B_ReVo;
import kr.car.vo.CarVO;
import kr.controller.Action;
import kr.util.PagingUtil;

public class ListAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null) pageNum = "1";
		
		B_ReDao dao = B_ReDao.getDao();
		int count = dao.getB_reCount();
	
		//페이지 처리
		PagingUtil page = new PagingUtil(Integer.parseInt(pageNum), count, 5, 10, "list.do");
		List<B_ReVo> list = null;
		if(count > 0) {
			list = dao.getListB_re(page.getStartRow(), page.getEndRow());
		}
		
		request.setAttribute("count", count);
		request.setAttribute("list", list);
		request.setAttribute("page", page.getPage());
		
		return "/WEB-INF/views/b_re/list.jsp";
	}

}
