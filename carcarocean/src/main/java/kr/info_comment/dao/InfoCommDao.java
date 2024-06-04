package kr.info_comment.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.info_comment.vo.InfoCommVo;
import kr.util.DBUtil;
import kr.util.DurationFromNow;
import kr.util.StringUtil;

public class InfoCommDao {
	private static InfoCommDao dao = new InfoCommDao();
	public static InfoCommDao getDao() {
		return dao;
	}
	private InfoCommDao() {}

	
	
	//정보공유 게시판 댓글 등록
	public void insertBoardComm(InfoCommVo infoComm) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int cnt = 0;
		try {
			conn = DBUtil.getConnection();
			sql = "INSERT INTO info_board_comment (info_board_comm_num, info_board_num, "
					+ "mem_num, info_board_comm_content, info_board_comm_reg) "
					+ "VALUES (info_board_comment_seq.nextval,?,?,?,SYSDATE)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(++cnt, infoComm.getInfo_board_num());
			pstmt.setInt(++cnt, infoComm.getMem_num());
			pstmt.setString(++cnt, infoComm.getInfo_board_comm_content());
			
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	//정보공유 게시판 댓글 개수
	public int getReplyBoardCount(int info_board_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT COUNT (*) FROM info_board_comment WHERE info_board_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, info_board_num);
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
	//정보공유 게시판 댓글 목록
	public List<InfoCommVo> getListReplyBoard(int start, int end, int info_board_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<InfoCommVo> list = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM "
					+ "(SELECT * FROM info_board_comment JOIN member USING(mem_num) "
					+ "WHERE info_board_num = ? ORDER BY info_board_comm_num DESC)a) "
					+ "WHERE rnum >= ? AND rnum <= ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, info_board_num);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rs = pstmt.executeQuery();
			list = new ArrayList<InfoCommVo>();
			while(rs.next()) {
				InfoCommVo comm = new InfoCommVo();
				comm.setInfo_board_comm_num(rs.getInt("info_board_comm_num"));
				comm.setInfo_board_comm_reg(DurationFromNow.getTimeDiffLabel(rs.getString("info_board_comm_reg")));
				comm.setInfo_board_comm_content(StringUtil.useBrNoHTML(rs.getString("info_board_comm_content")));
				comm.setInfo_board_comm_report(rs.getInt("info_board_comm_report"));
				comm.setInfo_board_num(rs.getInt("info_board_num"));
				comm.setMem_num(rs.getInt("mem_num"));
				comm.setMem_id(rs.getString("mem_id"));
				
				list.add(comm);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
}
