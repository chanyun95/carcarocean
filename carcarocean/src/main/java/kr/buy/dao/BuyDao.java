package kr.buy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import kr.buy.vo.BuyVo;
import kr.util.DBUtil;

public class BuyDao {
	// 싱글톤
	private static BuyDao dao = new BuyDao();
	public static BuyDao getDao() {
		return dao;
	}
	private BuyDao() {};
	
	public void insertBuy(BuyVo buy) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql = "INSERT INTO buy (buy_num, car_num, mem_num) VALUES (buy_seq.nextval,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(++cnt, buy.getCar_num());
			pstmt.setInt(++cnt, buy.getMem_num());
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
}
