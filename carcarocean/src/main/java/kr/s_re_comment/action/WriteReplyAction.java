package kr.s_re_comment.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.s_re_comment.dao.S_Re_CommentDao;
import kr.s_re_comment.vo.S_Re_CommentVo;

public class WriteReplyAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//Map은 JSON 응답으로 보낼 데이터를 포함한다. 그러므로 아래에 ObjectMapper 를 사용해야됨
				Map<String,String> mapAjax = 
						new HashMap<String,String>();
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) { //로그인이 안된경우
			mapAjax.put("result", "logout");
		}else {		
			//전송된 데이터 인코딩 타입 지정
			request.setCharacterEncoding("utf-8");
			//자바빈을(VO)를 생성해서 전송된 데이터 저장
			S_Re_CommentVo reply = new S_Re_CommentVo();
			reply.setMem_num(user_num);
			reply.setS_re_comm_content(request.getParameter("s_re_comm_content"));
			reply.setS_re_num(Integer.parseInt(request.getParameter("s_re_num")));//댓글의 부모 글번호
			
			//위에받은 3개의 데이터를 전달
			S_Re_CommentDao dao = S_Re_CommentDao.getDao();
			dao.insertReplySellReview(reply);
			//전달이 성공했다는 소식을 result에 저장해서 ajax로 보낸다.
			mapAjax.put("result","success");
		}
			// ObjectMapper를 사용하여 자바 객체(mapAjax)를 JSON 문자열로 변환
			ObjectMapper mapper = new ObjectMapper();
			String ajaxData = mapper.writeValueAsString(mapAjax); //문자열이 만들어짐
			
			//만든 문자열을 저장
			request.setAttribute("ajaxData",ajaxData);
			
			return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
