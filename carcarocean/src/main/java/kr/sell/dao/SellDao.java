package kr.sell.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import kr.sell.vo.SellVo;
import kr.util.DBUtil;

public class SellDao {
	// 싱글톤
	private SellDao dao = new SellDao();
	public SellDao getDao() {
		return dao;
	}
	private SellDao() {};
	
	//판매 등록
	public void insertSell(SellVo sell) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql = "INSETR INTO sell (sell_num, sell_mile, sell_cnumber, sell_maker, sell_cname, sell_name, sell_phone, sell_place1, sell_place2, sell_date, sell_reg) "
					+ "VALUES (sell_seq.nextval, ?, ?, ?, ?)";
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}

	//판매 조회
	
	//판매 수정
		
	//판매 취소

}
