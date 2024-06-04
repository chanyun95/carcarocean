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

public class WriteFavAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,Object> mapAjax = new HashMap<String,Object>();
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) { //로그인이 되지 않은 경우
			mapAjax.put("result", "logout");
		}else { //로그인 된 경우
			request.setCharacterEncoding("utf-8");
			int info_board_num = Integer.parseInt(request.getParameter("info_board_num"));
			InfoFavVo favVo = new InfoFavVo();
			favVo.setInfo_board_num(info_board_num);
			favVo.setMem_num(user_num);
			
			InfoBoardDao dao = InfoBoardDao.getDao();
			
			InfoFavVo db_fav = dao.selectFav(favVo);
			if(db_fav != null) {
				dao.deleteFav(db_fav);
				mapAjax.put("status", "noFav");
			}else {
				dao.insertFav(favVo);
				mapAjax.put("status", "yesFav");
			}
			mapAjax.put("result", "success");
			mapAjax.put("count", dao.selectFavCount(info_board_num));
		}
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	
	}

}
