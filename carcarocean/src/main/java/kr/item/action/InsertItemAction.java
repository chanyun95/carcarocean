package kr.item.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.item.dao.ItemDao;
import kr.item.vo.ItemVo;
import kr.member.dao.MemberDao;
import kr.util.FileUtil;

public class InsertItemAction implements Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		if(user_num==null) {
			return "/WEB-INF/views/member/loginForm.jsp";
		}
		
		//탈퇴 or 정지회원 금지
		if(user_auth<2) {
			return "redirect:/main/main.do";
		}
		request.setCharacterEncoding("utf-8");
		
		ItemVo item = new ItemVo();
		ItemDao itemDao = ItemDao.getDao();
		
		item.setItem_name(request.getParameter("item_name"));
		item.setItem_photo(FileUtil.createFiles(request));
		item.setItem_price(Integer.parseInt(request.getParameter("item_price")));
		item.setItem_detail(request.getParameter("item_detail"));
		MemberDao memberDao = MemberDao.getDao();
		item.setMember(memberDao.getMember(user_num));
		itemDao.insertItem(item);		
		return "redirect:/item/itemList.do";
	}
}
