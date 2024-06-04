package kr.buy.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.buy.dao.BuyDao;
import kr.buy.vo.BuyVo;
import kr.controller.Action;
import kr.util.PagingUtil;

public class MyBoughtAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Integer mem_num = (Integer) session.getAttribute("user_num");
        if(mem_num==null) {
        	return "redirect:/member/loginForm.do";
        }

        request.setCharacterEncoding("utf-8");

        // JSP 페이지 경로 반환
        String pageNum = request.getParameter("pageNum");
        if (pageNum == null) {
            pageNum = "1";
        }

        String keyfield = request.getParameter("keyfield");
        String keyword = request.getParameter("keyword");

        BuyDao buyDao = BuyDao.getDao();
        int count = buyDao.getMyBought(mem_num, keyfield, keyword).size();

        // 페이지 처리
        PagingUtil page = new PagingUtil(keyfield, keyword, Integer.parseInt(pageNum), count, 10, 10, "myBought.do");

        List<BuyVo> list = null;
        if (count > 0) {
            list = buyDao.getMyBought(mem_num, keyfield, keyword);
        }
        
        request.setAttribute("count", count);
        request.setAttribute("list", list);
        request.setAttribute("page", page.getPage());
        request.setAttribute("keyfield", keyfield);

        return "/WEB-INF/views/buy/myBought.jsp";
    }
}
