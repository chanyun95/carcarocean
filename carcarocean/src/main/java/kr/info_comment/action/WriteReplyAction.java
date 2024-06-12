package kr.info_comment.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.info_comment.dao.InfoCommDao;
import kr.info_comment.vo.InfoCommVo;

public class WriteReplyAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,String> mapAjax = new HashMap<String,String>();
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) { //로그인 되지 않은 상태
			mapAjax.put("result", "logout");
		}else {
			//로그인 된 상태
			request.setCharacterEncoding("utf-8");
			
			InfoCommVo comm = new InfoCommVo();
			comm.setMem_num(user_num);
			comm.setInfo_board_comm_content(request.getParameter("info_board_comm_content"));
			comm.setInfo_board_num(Integer.parseInt(request.getParameter("info_board_num")));
			
			InfoCommDao dao = InfoCommDao.getDao();
			dao.insertBoardComm(comm);
			
			mapAjax.put("result", "success");
		}
		
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
