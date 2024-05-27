package kr.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kr.board.vo.BoardVo;
import kr.util.DBUtil;

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
	
	//글 목록, 검색 글 목록
	
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
	//파일 삭제
	
	//글 수정
	
	//글 삭제
	
	//신고 등록
	
	//신고 개수
	
	
}
