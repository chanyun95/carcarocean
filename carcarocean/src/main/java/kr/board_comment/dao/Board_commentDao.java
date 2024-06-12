package kr.board_comment.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.board_comment.vo.Board_CommentVo;
import kr.util.DBUtil;
import kr.util.DurationFromNow;
import kr.util.StringUtil;

public class Board_commentDao {
	private static Board_commentDao dao = new Board_commentDao();
	public static Board_commentDao getDao() {
		return dao;
	}
	private Board_commentDao() {}
	
	//자유게시판 댓글 등록
	public void insertBoardComm(Board_CommentVo boardComm)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int cnt = 0;
		try {
			conn = DBUtil.getConnection();
			
			sql = "INSERT INTO board_comment(bor_comm_num,board_num,mem_num,bor_comm_content,bor_comm_reg) "
					+ "VALUES(board_comment_seq.nextval,?,?,?,SYSDATE)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(++cnt, boardComm.getBoard_num());
			pstmt.setInt(++cnt, boardComm.getMem_num());
			pstmt.setString(++cnt, boardComm.getBor_comm_content());
			
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	//자유게시판 댓글 개수
	public int getReplyBoardCount(int board_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT COUNT(*) FROM board_comment WHERE board_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_num);
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
	//자유게시판 댓글 목록
	public List<Board_CommentVo> getListReplyBoard(int start, int end, int board_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Board_CommentVo> list = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM "
					+ "(SELECT * FROM board_comment c "
					+ "JOIN member m ON c.mem_num = m.mem_num "
					+ "JOIN member_detail d ON m.mem_num = d.mem_num "
					+ "WHERE board_num=? ORDER BY bor_comm_num DESC)a) "
					+ "WHERE rnum >= ? AND rnum <= ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_num);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rs = pstmt.executeQuery();
			list = new ArrayList<Board_CommentVo>();
			while(rs.next()) {
				Board_CommentVo comm = new Board_CommentVo();
				comm.setBor_comm_num(rs.getInt("bor_comm_num"));
				comm.setBor_comm_reg(DurationFromNow.getTimeDiffLabel(rs.getString("bor_comm_reg")));
				comm.setBor_comm_content(StringUtil.useBrNoHTML(rs.getString("bor_comm_content")));
				comm.setBor_comm_report(rs.getInt("bor_comm_report"));
				comm.setBoard_num(rs.getInt("board_num"));
				comm.setMem_num(rs.getInt("mem_num"));
				comm.setMem_id(rs.getString("mem_id"));
				comm.setMem_photo(rs.getString("mem_photo"));
				
				list.add(comm);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}

	//자유게시판 댓글 신고 등록
	//자유게시판 댓글 신고 개수
}
