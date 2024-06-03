package kr.b_re_comment.action;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.b_re_comment.dao.B_Re_CommentDao;
import kr.b_re_comment.vo.B_Re_CommentVo;
import kr.controller.Action;
import kr.util.PagingUtil;

public class ListCommAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//전송된 데이터 인코딩 타입 지정
		request.setCharacterEncoding("utf-8");
		
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null) {
			pageNum = "1";
		}
		
		String rowCount = request.getParameter("rowCount");
		if(rowCount==null) {
			rowCount = "10";
		}
		int b_re_num = Integer.parseInt(request.getParameter("b_re_num"));
		B_Re_CommentDao dao = B_Re_CommentDao.getDao();
		int count = dao.getB_Re_CommCount(b_re_num);
		
		PagingUtil page = new PagingUtil(Integer.parseInt(pageNum), count, Integer.parseInt(rowCount));
		List<B_Re_CommentVo> list = null;
		if(count > 0) {
			list = dao.getListB_Re_Comm(page.getStartRow(), page.getEndRow(), b_re_num);
		}else {
			list = Collections.emptyList();
		}
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		
		Map<String,Object> mapAjax = new HashMap<String,Object>();
		mapAjax.put("count", count);
		mapAjax.put("list", list);
		//로그인한 사람이 작성자인지 체크하기 위해서 로그인한 회원번호 전송
		mapAjax.put("user_num", user_num);
		
		//JSON 문자열로 변환
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}
}
