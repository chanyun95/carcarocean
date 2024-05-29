package kr.board.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.board.dao.BoardDao;
import kr.board.vo.ReportBoardVO;
import kr.controller.Action;

public class WriteReportAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,Object> mapAjax = new HashMap<String,Object>();
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) { //로그인이 되지 않은 경우
			mapAjax.put("result", "logout");
		}else { //로그인 된 경우
			request.setCharacterEncoding("utf-8");
			
			int board_num = Integer.parseInt(request.getParameter("board_num"));
			ReportBoardVO reportVO = new ReportBoardVO();
			reportVO.setBoard_num(board_num);
			reportVO.setMem_num(user_num);
			
			BoardDao dao = BoardDao.getDao();
			ReportBoardVO db_report = dao.checkReport(reportVO);
			if(db_report != null) {
				mapAjax.put("status", "alreadyCheck");
			}else {
				dao.insertReport(reportVO);
				mapAjax.put("status", "yesReport");
			}
			mapAjax.put("result", "success");
			mapAjax.put("count", dao.checkReportCount(board_num));
		}
		
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
