package kr.sell.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import kr.sell.vo.SellVo;
import kr.util.DBUtil;

public class SellDao {
	// 싱글톤
	private static SellDao dao = new SellDao();
	public static SellDao getDao() {
		return dao;
	}
	private SellDao() {};
	
	//판매 등록
	public void insertSell(SellVo sell) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int cnt = 0;
		try {
			conn = DBUtil.getConnection();
			sql = "INSERT INTO sell (sell_num, sell_mile, sell_cnumber, sell_maker, sell_cname, sell_name, sell_phone,"
					+ " sell_place1, sell_place2, sell_date, mem_num) "
					+ "VALUES (sell_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(++cnt, sell.getSell_mile());
			pstmt.setString(++cnt,sell.getSell_cnumber());
			pstmt.setString(++cnt, sell.getSell_maker());
			pstmt.setString(++cnt, sell.getSell_cname());
			pstmt.setString(++cnt, sell.getSell_name());
			pstmt.setString(++cnt, sell.getSell_phone());
			pstmt.setString(++cnt, sell.getSell_place1());
			pstmt.setString(++cnt, sell.getSell_place2());
			pstmt.setInt(++cnt, sell.getSell_date());
			pstmt.setInt(++cnt, sell.getMem_num());
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}

	//판매 한 건 조회
	public SellVo getSell(int sell_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		SellVo sell = null;
		int cnt = 0;
		
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM SELL WHERE SELL_NUM=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(++cnt, sell_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				sell = new SellVo();
				sell.setSell_num(rs.getInt("sell_num"));
				sell.setSell_mile(rs.getInt("sell_mile"));
				sell.setSell_cnumber(rs.getString("sell_cnumber"));
				sell.setSell_maker(rs.getString("sell_maker"));
				sell.setSell_cname(rs.getString("sell_cname"));
				sell.setSell_name(rs.getString("sell_name"));
				sell.setSell_phone(rs.getString("sell_phone"));
				sell.setSell_place1(rs.getString("sell_place1"));
				sell.setSell_place2(rs.getString("sell_place2"));
				sell.setSell_date(rs.getInt("sell_date"));
				sell.setSell_check(rs.getInt("sell_check"));
				sell.setSell_reg(rs.getString("sel_reg"));
				sell.setSell_modify(rs.getString("sell_modify"));
				sell.setMem_num(rs.getInt("mem_num"));
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return sell;
	}
	
	//판매 목록, 검색 판매 목록
	public List<SellVo> getSellList(int start, int end, String keyfield, String keyword) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		List<SellVo> list = null;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM SELL";
			pstmt = conn.prepareStatement(sql);
			
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
	
	//판매 수정
		
	//판매 삭제
	
	//판매 갯수

}
