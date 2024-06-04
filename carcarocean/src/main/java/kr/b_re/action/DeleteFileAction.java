package kr.b_re.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import kr.b_re.dao.B_ReDao;
import kr.b_re.vo.B_ReVo;
import kr.controller.Action;
import kr.util.FileUtil;

public class DeleteFileAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,String> mapAjax = new HashMap<String,String>();			
		//전송된 데이터 인코딩 타입 지정
		request.setCharacterEncoding("utf-8");
		//전송된 데이터 반환
		int b_re_num = Integer.parseInt(request.getParameter("b_re_num"));
		
		B_ReDao dao = B_ReDao.getDao();
		B_ReVo db_b_re = dao.getB_Re(b_re_num);
		
		dao.deleteFile(b_re_num);
		
		//파일 삭제
		String[] photos = db_b_re.getB_re_photo().split(",");
		
		for(String photo : photos) {
			FileUtil.removeFile(request, photo);
		}
		
		mapAjax.put("result", "success");
		//JSON 데이터 생성
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
		
	}

}
