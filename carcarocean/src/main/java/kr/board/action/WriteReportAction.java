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

public class WriteReportAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,Object> mapAjax = new HashMap<String,Object>();
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) { //로그인 X
			mapAjax.put("result", "logout");
		}else { //로그인 O
			request.setCharacterEncoding("utf-8");
			
			int board_num = Integer.parseInt(request.getParameter("board_num"));
			BoardVo ReportVo = new BoardVo();
			ReportVo.setBoard_num(board_num);
			ReportVo.setMem_num(user_num);
			
			BoardDao dao = BoardDao.getDao();
			BoardVo db_report = dao.selectReport(board_num, user_num);
			if(db_report == null) {
				dao.insertReport(ReportVo);
				mapAjax.put("status", "yesReport");
			}else{
				mapAjax.put("status", "yesReport");
			}
			mapAjax.put("result", "success");
			mapAjax.put("count", dao.selectReportCount(board_num));
		}
		
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
			
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax.view.jsp";
	}

}
