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
	
	public void insertReservation(BuyVo buy) throws Exception{
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
	public int getReservationListCount() throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;
		try {
			conn = DBUtil.getConnection();
			// 구매상태가 예약인 것 만
			sql = "SELECT COUNT(*) FROM buy WHERE BUY_STATUS=0";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return count;
	}
	
	
	//구매 확정 ( 구매 확정 전으로 못돌림 그래서 buy_status=1 고정)
	public void insertBuy(BuyVo buy) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql = "UPDATE BUY SET BUY_STATUS=1 WHERE CAR_NUM=? AND MEM_NUM=?";
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
	public BuyVo getReservation(int mem_num, int car_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int cnt = 0;
		BuyVo buy = null;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM BUY WHERE MEM_NUM=? AND CAR_NUM=? AND BUY_STATUS=0";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(++cnt, mem_num);
			pstmt.setInt(++cnt, car_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				buy = new BuyVo();
				buy.setBuy_num(rs.getInt("buy_num"));
				buy.setBuy_reg(rs.getString("buy_reg"));
				buy.setCar_num(rs.getInt("car_num"));
				buy.setBuy_status(rs.getInt("buy_status"));
				buy.setMem_num(rs.getInt("mem_num"));
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return buy;
	}
	
	//구매 확정 정보
	public BuyVo getBuy(int mem_num, int car_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int cnt = 0;
		BuyVo buy = null;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM BUY WHERE MEM_NUM=? AND CAR_NUM=? AND BUY_STATUS=1";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(++cnt, mem_num);
			pstmt.setInt(++cnt, car_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				buy = new BuyVo();
				buy.setBuy_num(rs.getInt("buy_num"));
				buy.setBuy_reg(rs.getString("buy_reg"));
				buy.setCar_num(rs.getInt("car_num"));
				buy.setBuy_status(rs.getInt("buy_status"));
				buy.setMem_num(rs.getInt("mem_num"));
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return buy;
	}
	
	// 예약 취소
	public void deleteReservation(int mem_num, int car_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int cnt = 0;

		try {
			conn = DBUtil.getConnection();
			sql = "DELETE FROM buy where mem_num=? and car_num=? and buy_status=0";
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
