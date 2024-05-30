package kr.mypage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.member.dao.MemberDao;
import kr.member.vo.MemberVo;
import kr.sell.vo.SellVo;
import kr.util.DBUtil;

public class MyPageDao {
	//싱글톤
	private static MyPageDao dao = new MyPageDao();
	public static MyPageDao getDao() {
		return dao;
	}
	private MyPageDao() {};
	
	public static List<SellVo> getSellCurrent(int mem_num)throws Exception{
		List<SellVo> list = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int cnt = 0;
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			//zmember와 zmember_detail 테이블을 조인할 때
			//누락된 데이터가 보여야 id 중복 체크 가능
			sql = "SELECT sell_num,sell_cname,sell_check,sell_modify_check FROM sell WHERE mem_num = ?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(++cnt, mem_num);
			//SQL문 실행
			rs = pstmt.executeQuery();
			list = new ArrayList<>();
			while(rs.next()) {
				SellVo sell = new SellVo();
				sell.setSell_num(rs.getInt("sell_num"));
				sell.setSell_cname(rs.getString("sell_cname"));
				sell.setSell_check(rs.getInt("sell_check"));
				sell.setSell_modify_check(rs.getString("sell_modify_check"));
				list.add(sell);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
	
}