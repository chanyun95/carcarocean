package kr.info_board.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.info_board.dao.InfoBoardDao;
import kr.info_board.vo.InfoFavVo;

public class GetFavAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		int info_board_num = Integer.parseInt(request.getParameter("info_board_num"));
		
		Map<String,Object> mapAjax = 
	               new HashMap<String,Object>();

		HttpSession session = request.getSession();
		Integer user_num = 
				(Integer)session.getAttribute("user_num");
		InfoBoardDao dao = InfoBoardDao.getDao();
		if(user_num == null) { //로그인 되지 않은 경우
			mapAjax.put("status", "noFav");
		}else {
			InfoFavVo infoFav = dao.selectFav(new InfoFavVo(info_board_num, user_num));
			
			if(infoFav != null) {
				mapAjax.put("status", "yesFav");
			}else {
				mapAjax.put("status", "noFav");
			}
		}
		mapAjax.put("count", dao.selectFavCount(info_board_num));
		
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
