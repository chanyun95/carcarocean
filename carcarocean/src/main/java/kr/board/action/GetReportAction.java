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

public class GetReportAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		
		Map<String,Object> mapAjax = new HashMap<String,Object>();
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		BoardDao dao = BoardDao.getDao();
		if(user_num == null) { //로그인 되지 않은 경우
			mapAjax.put("status", "logout");
		}else { //로그인 된 경우
			
			ReportBoardVO reportVO = new ReportBoardVO(); 
            reportVO.setBoard_num(board_num);
            reportVO.setMem_num(user_num);
            
			ReportBoardVO report = dao.checkReport(reportVO);
			if(report != null) {
				mapAjax.put("status", "yesReport");
				
			}else {
				mapAjax.put("status", "noReport");
			}
		}
		int reportCount = dao.checkReportCount(board_num);
		mapAjax.put("count", reportCount);
		
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
