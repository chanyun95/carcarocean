package kr.board.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.board.dao.BoardDao;
import kr.board.vo.BoardVo;
import kr.controller.Action;

public class GetReportAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		
		Map<String,Object> mapAjax = new HashMap<String,Object>();
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		BoardDao dao = BoardDao.getDao();
		if(user_num == null) {
			mapAjax.put("status", "noReport");
		}else {
			BoardVo boardReport = dao.selectReport(board_num, user_num);
			if(boardReport != null) {
				mapAjax.put("status", "yesReport");
			}else {
				mapAjax.put("status", "noReport");
			}
		}
		mapAjax.put("count", dao.selectReportCount(board_num));
		
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
