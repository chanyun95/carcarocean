package kr.favorite_car.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kr.favorite_car.vo.Favorite_carVo;
import kr.util.DBUtil;

public class Favorite_carDao {
	// 싱글톤
	private static Favorite_carDao dao = new Favorite_carDao();
	public static Favorite_carDao getDao() {
		return dao;
	}
	private Favorite_carDao() {};
	
	public Favorite_carVo getFc(int car_num, int mem_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Favorite_carVo fc = null;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM favorite_car WHERE car_num=? and mem_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, car_num);
			pstmt.setInt(2, mem_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				fc = new Favorite_carVo();
				fc.setFav_num(rs.getInt("fav_num"));
				fc.setMem_num(rs.getInt("mem_num"));
				fc.setCar_num(rs.getInt("car_num"));
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return fc;
	}
	
	public void insertFav (int car_num, int mem_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			conn = DBUtil.getConnection();
			sql = "INSERT INTO favorite_car (fav_num, car_num, mem_num) VALUES (favorite_car_seq.nextval,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, car_num);
			pstmt.setInt(2, mem_num);
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	public void removeFav (int car_num, int mem_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			conn = DBUtil.getConnection();
			sql = "DELETE FROM favorite_car WHERE car_num=? AND mem_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, car_num);
			pstmt.setInt(2, mem_num);
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
}
