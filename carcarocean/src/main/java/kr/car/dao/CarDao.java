package kr.car.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import kr.car.vo.CarVO;
import kr.util.DBUtil;

public class CarDao {
	//싱글턴 패턴
	private static CarDao dao = new CarDao();
	public CarDao getDao() {
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
					+ "car_design_op, car_con_op, car_drive_op, checker_num) "
					+ "VALUES (car_seq.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(++cnt, car.getCar_maker());
			pstmt.setString(++cnt, car.getCar_name());
			pstmt.setInt(++cnt, car.getCar_size());
			pstmt.setDate(++cnt, car.getCar_birth());
			pstmt.setInt(++cnt, car.getCar_fuel_type());
			pstmt.setInt(++cnt, car.getCar_mile());
			pstmt.setInt(++cnt, car.getCar_price());
			pstmt.setString(++cnt, car.getCar_color());
			pstmt.setString(++cnt, car.getCar_photo());
			pstmt.setInt(++cnt, car.getCar_auto());
			pstmt.setInt(++cnt, car.getCar_fuel_efficiency());
			pstmt.setInt(++cnt, car.getCar_use());
			pstmt.setInt(++cnt, car.getCar_cc());
			pstmt.setString(++cnt, car.getCar_accident());
			pstmt.setInt(++cnt, car.getCar_owner_change());
			pstmt.setString(++cnt, car.getCar_design_op());
			pstmt.setString(++cnt, car.getCar_con_op());
			pstmt.setString(++cnt, car.getCar_drive_op());
			pstmt.setInt(++cnt, car.getChecker_num());
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	//차량 수, 검색
	//차량 목록, 검색 목록
	//차량 상세
	//차량 수정
	//차량 삭제
}
