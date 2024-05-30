package kr.b_re_comment.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.b_re_comment.vo.B_Re_CommentVo;
import kr.util.DBUtil;
import kr.util.StringUtil;

public class B_Re_CommentDao {
	private static B_Re_CommentDao dao = new B_Re_CommentDao();
	public static B_Re_CommentDao getDao() {
		return dao;
	}
	private B_Re_CommentDao() {}
	
	//댓글 등록
	public void insertB_Re_Comm(B_Re_CommentVo b_re_comm)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int cnt = 0;
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "INSERT INTO b_re_comment (b_re_comm_num,b_re_comm_content,b_re_num,"
					+ "mem_num)VALUES(b_re_comment_seq.nextval,?,?,?)";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setString(++cnt, b_re_comm.getB_re_comm_content());
			pstmt.setInt(++cnt, b_re_comm.getB_re_num());
			pstmt.setInt(++cnt, b_re_comm.getMem_num());
			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	//댓글 개수
	public int getB_Re_CommCount(int b_re_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;
		int cnt = 0;
		try {
			//커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			//SQL문
			sql = "SELECT COUNT(*) FROM b_re_comment WHERE b_re_num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(++cnt, b_re_num);
			//SQL문 실행
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
	//댓글 목록
	public List<B_Re_CommentVo> getListB_Re_Comm(int start, int end, int b_re_num)throws Exception{
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	List<B_Re_CommentVo> list = null;
	String sql = null;
	try {
	//커넥션풀로부터 커넥션 할당
		conn = DBUtil.getConnection();
		//SQL문 작성
		sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM "
			+"(SELECT * FROM b_re_comment JOIN member USING(mem_num) "
			+"WHERE b_re_num=? ORDER BY b_re_comm_num DESC)a) WHERE rnum >=? AND rnum < ?";
		//PreparedStatement 객체 생성
		pstmt = conn.prepareStatement(sql);
		//?에 데이터 바인딩
		pstmt.setInt(1, b_re_num);
		pstmt.setInt(2, start);
		pstmt.setInt(3, end);
		//SQL문 실행
		rs = pstmt.executeQuery();
		list = new ArrayList<B_Re_CommentVo>();
		while(rs.next()) {
			B_Re_CommentVo comm = new B_Re_CommentVo();
			comm.setB_re_comm_num(rs.getInt("b_re_comm_num"));
			//날짜 -> 1분전, 1시간전, 1일전 형식의 문자열로 변환
			/*
			reply.setRe_date(
					DurationFromNow.getTimeDiffLabel(
					          rs.getString("re_date")));
			if(rs.getString("re_modifydate")!=null) {
				reply.setRe_modifydate(
						DurationFromNow.getTimeDiffLabel(
						   rs.getString("re_modifydate")));
			}
			*/
			comm.setB_re_comm_content(StringUtil.useBrNoHTML(
					            rs.getString("b_re_comm_content")));
			comm.setB_re_comm_report(rs.getInt("b_re_comm_report"));
			comm.setB_re_num(rs.getInt("b_re_num"));
			comm.setMem_num(rs.getInt("mem_num"));//작성자 회원번호
			comm.setMem_id(rs.getString("mem_id"));//작성자 아이디
			comm.setB_re_comm_reg(rs.getString("b_re_comm_reg"));
			
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
