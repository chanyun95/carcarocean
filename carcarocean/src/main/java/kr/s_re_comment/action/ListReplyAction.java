package kr.s_re_comment.action;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.s_re_comment.dao.S_Re_CommentDao;
import kr.s_re_comment.vo.S_Re_CommentVo;
import kr.util.PagingUtil;

public class ListReplyAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//전송된 데이터 인코딩 타입 지정
		request.setCharacterEncoding("utf-8"); //숫자만 날라오긴 하지만 명시하는게 좋음
		// 요청 파라미터로부터 pageNum과 rowCount를 가져옴
		String pageNum = request.getParameter("pageNum");//현재 페이지 번호
		if(pageNum == null) {
			pageNum = "1";
		}
		String rowCount = request.getParameter("rowCount");//한 페이지에 표시할 댓글 수
		if(rowCount == null) {
			rowCount = "10";
		}
		// 게시판 번호를 요청 파라미터에서 가져옴
		int s_re_num = Integer.parseInt(request.getParameter("s_re_num"));
		// DAO를 통해 판매후기 게시판의 댓글 개수를 가져옴
		S_Re_CommentDao dao = S_Re_CommentDao.getDao();
		int count = dao.getReplySellReviewCount(s_re_num);
		/*
		 * ajax 방식으로 목록을 표시하기 때문에 PagingUtil은 페이지수 표시가
		 * 목적이 아니라 목록 데이터의 페이지 처리를 위해 rownum 번호를 구하는 것이 목적
		 */
		// 페이징 처리를 위한 PagingUtil 객체 생성
		PagingUtil page = new PagingUtil(Integer.parseInt(pageNum), count, Integer.parseInt(rowCount));
		
		//댓글 목록을 가져온다.
		List<S_Re_CommentVo> list = null;
		if(count > 0) {
			//page.getStartRow(), page.getEndRow() 는 PagingUtil에 있는 메서드이며 데이터의 시작행과 끝 행을 반환해주는 역할을한다.
			list = dao.getListReplySellReview(page.getStartRow(), page.getEndRow(), s_re_num);
		}else {
			list = Collections.emptyList(); // 빈 리스트를 만들어준다. 에러가 나지않게 하도록
		}
		// 현재 세션에서 로그인한 사용자 번호를 가져옴
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		
		// Ajax 응답 데이터로 보낼 맵 객체 생성
		Map<String, Object> mapAjax = new HashMap<String, Object>();
		mapAjax.put("count", count);
		mapAjax.put("list", list);
		mapAjax.put("user_num", user_num);
		
		// 맵 객체를 JSON 문자열로 변환
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		// 변환된 JSON 데이터를 요청 객체에 설정
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
