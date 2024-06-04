package kr.buy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.buy.vo.BuyVo;
import kr.car.vo.CarVO;
import kr.member.vo.MemberVo;
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
	public int getReservationListCount(String keyfield, String keyword) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String sub_sql = "";
		int count = 0;
		int cnt = 0;
		try {
			conn = DBUtil.getConnection();
			if(keyword!=null&&!"".equals(keyword)) {
				//검색 처리
				if(keyfield.equals("0")) {
					sub_sql += "AND mem_name LIKE '%' || ? || '%'";
				} else if(keyfield.equals("1")) {
					sub_sql += "AND mem_id LIKE '%' || ? || '%'";
				} else if(keyfield.equals("2")) {
					sub_sql += "AND mem_email LIKE '%' || ? || '%'";
				} else if(keyfield.equals("3")) {
					sub_sql += "AND (car_maker LIKE '%' || ? || '%' or car_name LIKE '%' || ? || '%')";
				}
			}
			// 구매상태가 예약인 것 만
			sql = "select count(*) from buy join car using (car_num) join member using (mem_num) "
					+ "join member_detail using (mem_num) where buy_status=0 "+ sub_sql +" ORDER BY buy_num";
			pstmt = conn.prepareStatement(sql);
			if(keyword!=null&&!"".equals(keyword)) {
				if(keyfield.equals("3")) {
					pstmt.setString(++cnt, keyword);
					pstmt.setString(++cnt, keyword);
				} else {
					pstmt.setString(++cnt, keyword);
				}
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
	public List<BuyVo> getReservationList(int start, int end, String keyfield, String keyword) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String sub_sql = "";
		int count = 0;
		int cnt = 0;
		List<BuyVo> list = null;
		try {
			conn = DBUtil.getConnection();
			if(keyword!=null&&!"".equals(keyword)) {
				//검색 처리
				if(keyfield.equals("0")) {
					sub_sql += "AND mem_name LIKE '%' || ? || '%'";
				} else if(keyfield.equals("1")) {
					sub_sql += "AND mem_id LIKE '%' || ? || '%'";
				} else if(keyfield.equals("2")) {
					sub_sql += "AND mem_email LIKE '%' || ? || '%'";
				} else if(keyfield.equals("3")) {
					sub_sql += "AND (car_maker LIKE '%' || ? || '%' or car_name LIKE '%' || ? || '%')";
				}
			}
			// 구매상태가 예약인 것 만
			sql = "SELECT * FROM (SELECT a.*,rownum rnum from (select * from buy join car using (car_num) join member using (mem_num) "
					+ "join member_detail using (mem_num) where buy_status=0 "+ sub_sql +"ORDER BY buy_num)a) WHERE rnum>=? AND rnum<=?";
			pstmt = conn.prepareStatement(sql);
			if(keyword!=null&&!"".equals(keyword)) {
				if(keyfield.equals("3")) {
					pstmt.setString(++cnt, keyword);
					pstmt.setString(++cnt, keyword);
				} else {
					pstmt.setString(++cnt, keyword);
				}
			}
			pstmt.setInt(++cnt,start);
			pstmt.setInt(++cnt,end);
			
			rs = pstmt.executeQuery();
			
			list = new ArrayList<>();
			
			while(rs.next()) {
				CarVO car = new CarVO();
				car.setCar_num(rs.getInt("car_num"));
				car.setCar_maker(rs.getString("car_maker"));
				car.setCar_name(rs.getString("car_name"));
				car.setCar_size(rs.getInt("car_size"));
				car.setCar_birth(rs.getString("car_birth"));
				car.setCar_cnumber(rs.getString("car_cnumber"));
				car.setCar_cc(rs.getInt("car_cc"));
				car.setCar_fuel_type(rs.getInt("car_fuel_type"));
				car.setCar_fuel_efficiency(rs.getInt("car_fuel_efficiency"));
				car.setCar_mile(rs.getInt("car_mile"));
				car.setCar_price(rs.getInt("car_price"));
				car.setCar_color(rs.getString("car_color"));
				car.setCar_photo(rs.getString("car_photo"));
				car.setCar_auto(rs.getInt("car_auto"));
				car.setCar_use(rs.getInt("car_use"));
				car.setCar_accident(rs.getString("car_accident"));
				car.setCar_owner_change(rs.getInt("car_owner_change"));
				car.setCar_design_op(rs.getString("car_design_op"));
				car.setCar_con_op(rs.getString("car_con_op"));
				car.setCar_drive_op(rs.getString("car_drive_op"));
				car.setCar_status(rs.getInt("car_status"));
				car.setChecker_num(rs.getInt("checker_num"));
				car.setCar_checker_opinion(rs.getString("car_checker_opinion"));
				car.setMem_num(rs.getInt("mem_num"));
				
				MemberVo member = new MemberVo();
				member = new MemberVo();
				member.setMem_num(rs.getInt("mem_num"));
				member.setMem_name(rs.getString("mem_name"));
				member.setMem_id(rs.getString("mem_id"));
				member.setMem_email(rs.getString("mem_email"));
				member.setMem_auth(rs.getInt("mem_auth"));
				member.setMem_name(rs.getString("mem_name"));
				member.setMem_phone(rs.getString("mem_phone"));
				member.setMem_zipcode(rs.getString("mem_zipcode"));
				member.setMem_address1(rs.getString("mem_address1"));
				member.setMem_address2(rs.getString("mem_address2"));
				member.setMem_photo(rs.getString("mem_photo"));
				member.setMem_reg(rs.getString("mem_reg"));
				member.setMem_modify(rs.getString("mem_modify"));
				
				BuyVo buy = new BuyVo();
				//굳이 필요 없긴한데
				buy.setBuy_num(rs.getInt("buy_num"));
				buy.setMem_num(rs.getInt("mem_num"));
				buy.setCar_num(rs.getInt("car_num"));
				buy.setCar(car);
				buy.setMember(member);
				list.add(buy);
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
	
	//구매 확정 ( 구매 확정 전으로 못돌림 그래서 buy_status=1 고정)
	public void insertBuy(int car_num, int mem_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql = "UPDATE BUY SET BUY_STATUS=1, buy_reg=sysdate WHERE CAR_NUM=? AND MEM_NUM=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(++cnt, car_num);
			pstmt.setInt(++cnt, mem_num);
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
