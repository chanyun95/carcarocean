package kr.checker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.checker.vo.CheckerVo;
import kr.util.DBUtil;

public class CheckerDao {
	private static CheckerDao dao = new CheckerDao();
	public static CheckerDao getDao() {
		return dao;
	}
	private CheckerDao() {};
	
	public List<CheckerVo>getCheckerAllList() throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CheckerVo> checkerList;
		String sql = null;

		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM checker";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			checkerList = new ArrayList<>();
			
			while(rs.next()) {
				CheckerVo checker = new CheckerVo();
				checker.setChecker_num(rs.getInt("checker_num"));
				checker.setChecker_name(rs.getString("checker_name"));
				checker.setChecker_photo(rs.getString("checker_photo"));
				checker.setChecker_company(rs.getString("checker_company"));
				checker.setChecker_phone(rs.getString("checker_phone"));
				checkerList.add(checker);
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}

		return checkerList;
	}
}
