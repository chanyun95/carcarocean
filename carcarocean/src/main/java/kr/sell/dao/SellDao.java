package kr.sell.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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
	// 전체 판매 목록 (검수 대기 중) - 관리자가 보는 목록
	public List<SellVo> getSellList(int start, int end, String keyfield, String keyword, String sell_check) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String sub_sql = "";
		List<SellVo> list = null;
		int cnt = 0;
		
		try {
			conn = DBUtil.getConnection();
			if(keyword!=null&&!"".equals(keyword)) {
				//검색 처리
				if(keyfield.equals("0")) {
					sub_sql += "AND sell_name LIKE '%' || ? || '%'";
				} else if(keyfield.equals("1")) {
					sub_sql += "AND sell_maker LIKE '%' || ? || '%'";
				} else if(keyfield.equals("2")) {
					sub_sql += "AND sell_cname LIKE '%' || ? || '%'";
				}
			}
			sql = "SELECT * FROM (SELECT a.*,rownum rnum from (select * from sell WHERE sell_check=? " + sub_sql + " ORDER BY sell_reg DESC)a) WHERE rnum>=? AND rnum<=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(++cnt, sell_check);
			if(keyword!=null&&!"".equals(keyword)) {
				//검색 처리? 바운딩
				pstmt.setString(++cnt, keyword);
			}
			pstmt.setInt(++cnt, start);
			pstmt.setInt(++cnt, end);
			
			rs = pstmt.executeQuery();
			list = new ArrayList<>();
			while(rs.next()) {
				SellVo sell = new SellVo();
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
				sell.setSell_reg(rs.getString("sell_reg"));
				sell.setSell_modify(rs.getString("sell_modify"));
				sell.setMem_num(rs.getInt("mem_num"));
				list.add(sell);
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
		// 전체 판매 목록 갯수(검수 대기 중) - 관리자가 보는 목록
		public int getSellListCount(String keyfield, String keyword, String sell_check) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
			String sub_sql = "";
			//sql문에 쓰는 cnt
			int cnt = 0;
			//return값
			int count = 0;
			try {
				conn = DBUtil.getConnection();
				if(keyword!=null&&!"".equals(keyword)) {
					//검색 처리
					if(keyfield.equals("0")) {
						sub_sql += "AND sell_name LIKE '%' || ? || '%'";
					} else if(keyfield.equals("1")) {
						sub_sql += "AND sell_maker LIKE '%' || ? || '%'";
					} else if(keyfield.equals("2")) {
						sub_sql += "AND sell_cname LIKE '%' || ? || '%'";
					}
				}
				sql = "SELECT COUNT(*) FROM sell WHERE sell_check=? " + sub_sql;
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(++cnt, sell_check);
				if(keyword!=null&&!"".equals(keyword)) {
					pstmt.setString(++cnt, keyword);
				}
				
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
				sell.setSell_modify_check(rs.getString("sell_modify_check"));
				sell.setSell_reg(rs.getString("sell_reg"));
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
	
	//판매 상태 수정(sell_check) : 검수 상태 변경하는 메서드
	public void updateSellCheck(int sell_num,int sell_check) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			conn = DBUtil.getConnection();
			sql = "UPDATE sell SET sell_check=?, sell_modify_check=SYSDATE WHERE sell_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sell_check);
			pstmt.setInt(2, sell_num);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	
	//판매 삭제
	public void deleteSell(int sell_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			conn = DBUtil.getConnection();
			sql = "DELETE FROM sell WHERE sell_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sell_num);
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	//판매 갯수

}
