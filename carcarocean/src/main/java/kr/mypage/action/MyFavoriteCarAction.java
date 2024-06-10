package kr.mypage.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.ant.jmx.JMXAccessorQueryTask;

import kr.car.dao.CarDao;
import kr.car.vo.CarVO;
import kr.controller.Action;
import kr.favorite_car.dao.Favorite_carDao;
import kr.favorite_car.vo.Favorite_carVo;
import kr.member.dao.MemberDao;
import kr.member.vo.MemberVo;
import kr.mypage.dao.MyPageDao;
import kr.sell.vo.SellVo;
import kr.util.PagingUtil;

public class MyFavoriteCarAction implements Action{

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
		MemberDao dao = MemberDao.getDao();
		MemberVo member = dao.getMember(user_num);
		MyPageDao dao1 = MyPageDao.getDao();

		List<Favorite_carVo> list = dao1.myFavList(user_num);

		request.setAttribute("list",list);
		request.setAttribute("member", member);
		return "/WEB-INF/views/member/myFavoriteCar.jsp";
	}

}
