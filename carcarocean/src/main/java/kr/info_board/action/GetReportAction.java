package kr.info_board.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.info_board.dao.InfoBoardDao;
import kr.info_board.vo.ReportInfoVo;

public class GetReportAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		int info_board_num = Integer.parseInt(request.getParameter("info_board_num"));
		
		Map<String,Object> mapAjax = new HashMap<String,Object>();
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		InfoBoardDao dao = InfoBoardDao.getDao();
		if(user_num == null) { //로그인이 되지 않은 경우
			mapAjax.put("result", "logout");
		}else { //로그인 된 경우
			ReportInfoVo reportVo = new ReportInfoVo();
			reportVo.setInfo_board_num(info_board_num);
			reportVo.setMem_num(user_num);
			
			ReportInfoVo report = dao.checkReport(reportVo);
			if(report != null) {
				mapAjax.put("status", "yesReport");
			}else {
				mapAjax.put("status", "noReport");
			}
		}
		int reportCount = dao.checkReportCount(info_board_num);
		mapAjax.put("count", reportCount);
		
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
