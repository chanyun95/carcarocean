package kr.b_re_comment.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.b_re_comment.dao.B_Re_CommentDao;
import kr.b_re_comment.vo.B_Re_CommentVo;
import kr.controller.Action;

public class WriteCommAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,String> mapAjax = new HashMap<String,String>();
		HttpSession session = request.getSession();
		Integer user_num =(Integer)session.getAttribute("user_num");
		if(user_num==null) {//로그인이 되지 않은 경우
			mapAjax.put("result", "logout");
		}else {//로그인이 된 경우
			//전송된 데이터 인코딩 타입 지정
			request.setCharacterEncoding("utf-8");
			//자바빈(VO)을 생성해서 전송된 데이터 저장
			B_Re_CommentVo comm = new B_Re_CommentVo();
			comm.setMem_num(user_num); //댓글 작성자 회원번호
			comm.setB_re_comm_content(request.getParameter("b_re_comm_content"));
			//댓글의 부모 글 번호
			comm.setB_re_num(Integer.parseInt(request.getParameter("b_re_num")));
			B_Re_CommentDao dao = B_Re_CommentDao.getDao();
			dao.insertB_Re_Comm(comm);
			
			mapAjax.put("result", "success");
		}
		//JSON 데이터로 반환
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}
}
