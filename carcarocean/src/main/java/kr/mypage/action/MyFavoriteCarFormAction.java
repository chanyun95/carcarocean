package kr.mypage.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.car.dao.CarDao;
import kr.car.vo.CarVO;
import kr.controller.Action;
import kr.favorite_car.vo.Favorite_carVo;
import kr.member.dao.MemberDao;
import kr.member.vo.MemberVo;
import kr.mypage.dao.MyPageDao;

public class MyFavoriteCarFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		//int가 아닌 Integer를 쓰는 이유는 int는 null값을 받을 수 없다. Integer는 널값을 받을 수 있기 때문에 아래에 로그인 유무를 체크할 수 있다.
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) { //로그인이 되지 않은 경우 즉, 로그인값이 없는경우
			return "redirect:/member/loginForm.do";
		}
		
		//로그인이 된경우
		//회원정보
		MemberDao dao1 = MemberDao.getDao();
		MemberVo member = dao1.getMember(user_num);
		
		//MyPageDao dao = MyPageDao.getDao();
		List<Favorite_carVo> favList = MyPageDao.myFavCar(user_num);
		List<CarVO> carList = new ArrayList<>();
		CarDao cdao = CarDao.getDao();
		for(Favorite_carVo fav:favList) {
			carList.add(cdao.getCar(fav.getCar_num()));
		}
		

		request.setAttribute("carList", carList);
		request.setAttribute("member", member);
		return "/WEB-INF/views/member/myFavoriteCarForm.jsp";
	}

}
