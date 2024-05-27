package kr.board_comment.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import kr.board_comment.vo.Board_CommentVo;
import kr.util.DBUtil;

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
			
			sql = "INSERT INTO board_comment(bor_comm_num,board_num,mem_num,bor_comm_content) "
					+ "VALUES(board_comment_seq.nextval,?,?,?)";
			
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
	//자유게시판 댓글 목록
	//자유게시판 댓글 수정
	//자유게시판 댓글 삭제
	//자유게시판 댓글 신고 등록
	//자유게시판 댓글 신고 개수
}
