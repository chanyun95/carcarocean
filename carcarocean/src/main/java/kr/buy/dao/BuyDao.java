package kr.buy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
	
	public BuyVo getBuyByMemCar(int mem_num, int car_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int cnt = 0;
		BuyVo buy = null;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM BUY WHERE MEM_NUM=? AND CAR_NUM=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(++cnt, mem_num);
			pstmt.setInt(++cnt, car_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				buy = new BuyVo();
				buy.setBuy_num(rs.getInt("buy_num"));
				buy.setBuy_reg(rs.getString("buy_reg"));
				buy.setCar_num(rs.getInt("car_num"));
				buy.setMem_num(rs.getInt("mem_num"));
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return buy;
	}
	
	public void deleteBuy(int mem_num, int car_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int cnt = 0;

		try {
			conn = DBUtil.getConnection();
			sql = "DELETE FROM buy where mem_num=? and car_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(++cnt, mem_num);
			pstmt.setInt(++cnt, car_num);
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}

	}
}
