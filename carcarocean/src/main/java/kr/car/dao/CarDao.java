package kr.car.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.car.vo.CarVO;
import kr.util.DBUtil;

public class CarDao {
	//싱글턴 패턴
	private static CarDao dao = new CarDao();
	public static CarDao getDao() {
		return dao;
	}
	private CarDao() {};
	
	//차량 등록
	public void insertCar(CarVO car) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int cnt = 0;
		try {
			conn = DBUtil.getConnection();
			sql = "INSERT INTO car (car_num, car_maker, car_name, car_size, car_birth, "
					+ "car_fuel_type, car_mile, car_price, car_color, car_photo, car_auto, "
					+ "car_fuel_efficiency, car_use, car_cc, car_accident, car_owner_change, "
					+ "car_design_op, car_con_op, car_drive_op, checker_num, car_checker_opinion) "
					+ "VALUES (car_seq.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(++cnt, car.getCar_maker());
			pstmt.setString(++cnt, car.getCar_name());
			pstmt.setInt(++cnt, car.getCar_size());
			pstmt.setString(++cnt, car.getCar_birth());
			pstmt.setInt(++cnt, car.getCar_fuel_type());
			pstmt.setInt(++cnt, car.getCar_mile());
			pstmt.setInt(++cnt, car.getCar_price());
			pstmt.setString(++cnt, car.getCar_color());
			pstmt.setString(++cnt, car.getCar_photo());
			pstmt.setInt(++cnt, car.getCar_auto());
			pstmt.setFloat(++cnt, car.getCar_fuel_efficiency());
			pstmt.setInt(++cnt, car.getCar_use());
			pstmt.setInt(++cnt, car.getCar_cc());
			pstmt.setString(++cnt, car.getCar_accident());
			pstmt.setInt(++cnt, car.getCar_owner_change());
			pstmt.setString(++cnt, car.getCar_design_op());
			pstmt.setString(++cnt, car.getCar_con_op());
			pstmt.setString(++cnt, car.getCar_drive_op());
			pstmt.setInt(++cnt, car.getChecker_num());
			pstmt.setString(++cnt, car.getCar_checker_opinion());
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	//차량 전체 수, 검색 수
	
	//차량 전체 목록, 검색 목록
	public List<CarVO> getListCar(int start, int end, String keyfield, String keyword, int status) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CarVO> list = null;
		String sql = null;
		String sub_sql = "";
		int cnt = 0;
		
		try {
			conn = DBUtil.getConnection();
			
			if(keyword != null && !"".equals(keyword)) {
				if(keyfield.equals("1")) sub_sql += "AND car_maker LIKE '%' || ? || '%'";
				else if(keyfield.equals("2")) sub_sql += "AND car_name LIKE '%' || ? || '%'";
				else if(keyfield.equals("3")) sub_sql += "AND car_size LIKE '%' || ? || '%'";
			}
			
			sql =
			"SELECT * FROM (SELECT a.*, rownum rnum FROM (SELECT * FROM car WHERE car_status <= ? "
			+ sub_sql + " ORDER BY car_num DESC)a) WHERE rnum >=? AND rnum <=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(++cnt, status);
			if(keyword != null && !"".equals(keyword)) {
				pstmt.setString(++cnt, keyword);
			}
			pstmt.setInt(++cnt, start);
			pstmt.setInt(++cnt, end);
			rs = pstmt.executeQuery();
			list = new ArrayList<>();
			while(rs.next()) {
				CarVO car = new CarVO();
				car.setCar_num(rs.getInt("car_num"));
				car.setCar_maker(rs.getString("car_maker"));
				car.setCar_name(rs.getString("car_name"));
				car.setCar_size(rs.getInt("car_size"));
				car.setCar_birth(rs.getString("car_birth"));
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
				
				list.add(car);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally{
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
	//차량 상세
	public CarVO getCar(int car_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		CarVO car = null;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM car WHERE car_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, car_num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				car = new CarVO();
				car.setCar_num(rs.getInt("car_num"));
				car.setCar_maker(rs.getString("car_maker"));
				car.setCar_name(rs.getString("car_name"));
				car.setCar_size(rs.getInt("car_size"));
				car.setCar_birth(rs.getString("car_birth"));
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
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return car;
	}
	//차량 수정
	//차량 삭제
	
	//차량 갯수 조회 (검수자 번호)
	public int getCarCountByChecker(int checker_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;

		try {
			conn = DBUtil.getConnection();
			sql = "SELECT count(*) FROM car WHERE checker_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, checker_num);
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
}
