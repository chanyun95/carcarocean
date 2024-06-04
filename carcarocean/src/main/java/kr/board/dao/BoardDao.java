package kr.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.board.vo.BoardVo;
import kr.board.vo.ReportBoardVO;
import kr.util.DBUtil;
import kr.util.StringUtil;

public class BoardDao {
	private static BoardDao dao = new BoardDao();
	public static BoardDao getDao() {
		return dao;
	}
	private BoardDao() {}
	
	//글 등록
	public void insertBoard(BoardVo board) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int cnt = 0;
		try {
			conn = DBUtil.getConnection();
			sql = "INSERT INTO board (board_num, mem_num, board_title, board_content, "
					+ "board_photo) VALUES (board_seq.nextval,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(++cnt, board.getMem_num());
			pstmt.setString(++cnt, board.getBoard_title());
			pstmt.setString(++cnt, board.getBoard_content());
			pstmt.setString(++cnt, board.getBoard_photo());
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	//총 글의 수, 검색
	public int getBoardCount(String keyfield, String keyword) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String sub_sql = "";
		int count = 0;
		int cnt = 0;
		try {
			conn = DBUtil.getConnection();
			if(keyword != null && !"".equals(keyword)) {
				if(keyfield.equals("1")) sub_sql += "WHERE board_title LIKE '%' || ? || '%'";
				else if(keyfield.equals("2")) sub_sql += "WHERE mem_id LIKE '%' || ? || '%'";
				else if(keyfield.equals("3")) sub_sql += "WHERE board_content LIKE '%' || ? || '%'";
			}
			sql = "SELECT COUNT(*) FROM board JOIN member USING(mem_num) " + sub_sql;
			pstmt = conn.prepareStatement(sql);
			if(keyword != null && !"".equals(keyword)) {
				pstmt.setString(++cnt, keyword);
			}
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return count;
	}
	//글 목록, 검색 글 목록
	public List<BoardVo> getListBoard(int start, int end, String keyfield, String keyword) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BoardVo> list = null;
		String sql = null;
		String sub_sql = "";
		int cnt = 0;
		try {
			conn  = DBUtil.getConnection();
			if(keyword != null && !"".equals(keyword)) {
				//검색 처리
				if(keyfield.equals("1")) sub_sql += " WHERE board_title LIKE '%' || ? || '%'";
				else if(keyfield.equals("2")) sub_sql += " WHERE mem_id LIKE '%' || ? || '%'";
				else if(keyfield.equals("3")) sub_sql += " WHERE board_content LIKE '%' || ? || '%'";
			}
			sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM "
					+ "(SELECT * FROM board JOIN member USING(mem_num) " + sub_sql
					+ " ORDER BY board_num DESC)a) WHERE rnum >=? AND rnum <=?";
			
			pstmt = conn.prepareStatement(sql);
			if(keyword != null && !"".equals(keyword)) {
				pstmt.setString(++cnt, keyword);
			}
			pstmt.setInt(++cnt, start);
			pstmt.setInt(++cnt, end);
	        
			rs = pstmt.executeQuery();
			list = new ArrayList<BoardVo>();
			while(rs.next()) {
				BoardVo board = new BoardVo();
				board.setBoard_num(rs.getInt("board_num"));
				board.setBoard_title(StringUtil.useBrNoHTML(rs.getString("board_title")));
				board.setBoard_hit(rs.getInt("board_hit"));
				board.setBoard_reg(rs.getString("board_reg"));
				board.setMem_id(rs.getString("mem_id"));
				board.setBoard_report(rs.getInt("board_report"));
				list.add(board);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
	//글 상세
	public BoardVo getBoard(int board_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVo board = null;
		String sql = null;
		int cnt = 0;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT board.*, member.mem_id, member_detail.mem_photo " +
				      "FROM board " +
				      "JOIN member ON board.mem_num = member.mem_num " +
				      "LEFT OUTER JOIN member_detail ON member.mem_num = member_detail.mem_num " +
				      "WHERE board_num=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(++cnt, board_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				board = new BoardVo();
				board.setBoard_num(rs.getInt("board_num"));
				board.setBoard_title(rs.getString("board_title"));
				board.setBoard_content(rs.getString("board_content"));
				board.setBoard_hit(rs.getInt("board_hit"));
				board.setBoard_modify(rs.getString("board_modify"));
				board.setBoard_Photo(rs.getString("board_photo"));
				board.setMem_num(rs.getInt("mem_num"));
				board.setMem_id(rs.getString("mem_id"));
				board.setMem_photo(rs.getString("mem_photo"));
				board.setBoard_reg(rs.getString("board_reg"));
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return board;
	}
	//조회수 증가
	public void updateReadCount(int board_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int cnt = 0;
		try {
			conn = DBUtil.getConnection();
			sql = "UPDATE board SET board_hit=board_hit+1 WHERE board_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(++cnt, board_num);
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//글 수정
	public void updateBoard(BoardVo board) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		String sub_sql = "";
		int cnt = 0;
		try {
			conn = DBUtil.getConnection();
			if(board.getBoard_photo() != null && !"".equals(board.getBoard_photo())) {
				sub_sql += ",board_photo=?";
			}
			sql = "UPDATE board SET board_title=?, board_content=?, board_modify=SYSDATE" + sub_sql 
					+ " WHERE board_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(++cnt, board.getBoard_title());
			pstmt.setString(++cnt, board.getBoard_content());
			if(board.getBoard_photo() != null && !"".equals(board.getBoard_photo())) {
				pstmt.setString(++cnt, board.getBoard_photo());
			}
			pstmt.setInt(++cnt, board.getBoard_num());
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	//글 삭제
	public void deleteBoard(int board_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		String sql = null;
		int cnt = 0;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			
			sql = "DELETE FROM board_comment WHERE board_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(++cnt, board_num);
			pstmt.executeUpdate();
			
			cnt = 0;
			
			sql="DELETE FROM board WHERE board_num=?";
			pstmt2 = conn.prepareStatement(sql);
			pstmt2.setInt(++cnt, board_num);
			pstmt2.executeUpdate();
			
			conn.commit();
		}catch(Exception e) {
			conn.rollback();
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt2, null);
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	//파일 삭제
	public void deleteFile(int board_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int cnt = 0;
		try {
			conn = DBUtil.getConnection();
			sql = "UPDATE board SET board_photo='' WHERE board_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(++cnt, board_num);
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	/*
	 * //신고 등록 public void insertReport(ReportBoardVO reportVO) throws Exception{
	 * Connection conn = null; PreparedStatement pstmt = null; String sql = null;
	 * try { conn = DBUtil.getConnection(); sql =
	 * "INSERT INTO report_board (report_board_num,board_num, mem_num) VALUES (report_board_num_seq.nextval,?,?)"
	 * ; pstmt = conn.prepareStatement(sql); pstmt.setInt(1,
	 * reportVO.getBoard_num()); pstmt.setInt(2, reportVO.getMem_num());
	 * pstmt.executeUpdate();
	 * 
	 * }catch(Exception e) { throw new Exception(e); }finally {
	 * DBUtil.executeClose(null, pstmt, conn); } }
	 */

	//신고 등록 
		public void insertReport(ReportBoardVO reportVO) throws Exception {
		Connection conn = null; 
		PreparedStatement pstmt = null; String
		sqlInsertReport ="INSERT INTO report_board (report_board_num, board_num, mem_num) VALUES (report_board_num_seq.nextval, ?, ?)";
		String sqlUpdateBoard ="UPDATE board SET board_report = board_report + 1 WHERE board_num = ?";

		try { 
			conn = DBUtil.getConnection(); pstmt =
			conn.prepareStatement(sqlInsertReport); pstmt.setInt(1,
			reportVO.getBoard_num()); 
			pstmt.setInt(2, reportVO.getMem_num());
			pstmt.executeUpdate();
			// 두 번째 쿼리를 실행하기 전에 PreparedStatement를 재사용합니다.
			// 기존매개변수를 지웁니다.
			pstmt.clearParameters();
			pstmt = conn.prepareStatement(sqlUpdateBoard);
			pstmt.setInt(1,reportVO.getBoard_num());
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(null, pstmt, conn); 
		}
	}
	 
	
	//회원번호와 게시물 번호를 이용한 신고 정보
	public ReportBoardVO checkReport(ReportBoardVO reportVO) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ReportBoardVO check = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM report_board WHERE board_num=? AND mem_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reportVO.getBoard_num());
			pstmt.setInt(2, reportVO.getMem_num());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				check = new ReportBoardVO();
				check.setBoard_num(rs.getInt("board_num"));
				check.setMem_num(rs.getInt("mem_num"));
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return check;
	}
	
	//신고 개수
	public int checkReportCount(int board_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT board_report  FROM board WHERE board_num=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt("board_report");
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return count;
	}
}
