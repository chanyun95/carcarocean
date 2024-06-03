package kr.board_comment.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.board_comment.dao.Board_commentDao;
import kr.board_comment.vo.Board_CommentVo;
import kr.controller.Action;

public class WriteReplyAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,String> mapAjax = new HashMap<String,String>();
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) {
			mapAjax.put("result", "logout");
		}else {
			request.setCharacterEncoding("utf-8");
			
			Board_CommentVo comm = new Board_CommentVo();
			comm.setMem_num(user_num);
			comm.setBor_comm_content(request.getParameter("bor_comm_content"));
			comm.setBoard_num(Integer.parseInt(request.getParameter("board_num")));
			
			Board_commentDao dao = Board_commentDao.getDao();
			dao.insertBoardComm(comm);
			
			mapAjax.put("result", "success");
		}
		
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
