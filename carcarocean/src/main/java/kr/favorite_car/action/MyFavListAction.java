package kr.favorite_car.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.car.dao.CarDao;
import kr.car.vo.CarVO;
import kr.controller.Action;
import kr.favorite_car.dao.Favorite_carDao;
import kr.favorite_car.vo.Favorite_carVo;
import kr.util.PagingUtil;

public class MyFavListAction implements Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//로그인 체크
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		//로그인 안되어 있을 시 로그인 폼으로 이동
		if(user_num==null) {
			return "/WEB-INF/views/common/logout.jsp";
		}
		
		request.setCharacterEncoding("utf-8");
		
		// 확인 끝
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null) {
			pageNum="1";
		}
		
		Favorite_carDao favDao = Favorite_carDao.getDao();
		int count = favDao.getFavListCount(user_num);
		
		//페이지 처리
		PagingUtil page = new PagingUtil(null, null, Integer.parseInt(pageNum),count,15,10,"myFavList.do");
		
		List<Favorite_carVo> favList = null;
		List<CarVO> carList = null;
		CarDao cDao = CarDao.getDao();
		if(count > 0) {
			favList = favDao.getFavList(page.getStartRow(), page.getEndRow(),user_num);
			carList = new ArrayList<>();
			for(Favorite_carVo fav : favList) {
				carList.add(cDao.getCar(fav.getCar_num()));
			}
		}
		
		request.setAttribute("count", count);
		request.setAttribute("favList", favList);
		request.setAttribute("carList", carList);
		request.setAttribute("page", page.getPage());
		
		return "/WEB-INF/views/fav/myFavList.jsp";
	}
}
