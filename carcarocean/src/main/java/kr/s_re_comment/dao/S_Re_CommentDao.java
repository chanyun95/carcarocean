package kr.s_re_comment.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.s_re_comment.vo.S_Re_CommentVo;
import kr.util.DBUtil;
import kr.util.DurationFromNow;

public class S_Re_CommentDao {
	//싱글턴 패턴
	private static S_Re_CommentDao instance = new S_Re_CommentDao();
	
	public static S_Re_CommentDao getDao() {
		return instance;
	}
	private S_Re_CommentDao() {}
	
	//댓글등록
	public void insertReplySellReview(S_Re_CommentVo s_re_comment) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql =null;
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			sql = "INSERT INTO s_re_comment (s_re_comm_num,s_re_comm_content,"
					+ "mem_num,s_re_num) VALUES (s_re_comment_seq.nextval,?,?,?)";
			pstmt =conn.prepareStatement(sql);
			pstmt.setString(1,s_re_comment.getS_re_comm_content());
			pstmt.setInt(2,s_re_comment.getMem_num());
			pstmt.setInt(3,s_re_comment.getS_re_num());
			
			
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	//댓글개수
	public int getReplySellReviewCount (int s_re_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql =null;
		ResultSet rs = null;
		int count = 0;
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql="SELECT COUNT(*) FROM s_re_comment WHERE s_re_num=?";
			pstmt =conn.prepareStatement(sql);
			pstmt.setInt(1, s_re_num);
			//SQL문 실행
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1); //첫 번째 열의 값을 가져온다.
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return count;
	}
	//댓글목록
	public List<S_Re_CommentVo> getListReplySellReview(int start, int end, int s_re_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql =null;
		ResultSet rs = null;
		int cnt = 0;
		List<S_Re_CommentVo> list = null;
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql="SELECT * FROM (SELECT a.*, rownum rnum FROM "
				+"(SELECT * FROM s_re_comment JOIN member USING(mem_num) "
				+"WHERE s_re_num=? ORDER BY s_re_comm_num DESC)a) WHERE rnum >=? AND rnum <?";
			//PreparedStatment 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, s_re_num);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			//SQL문 실행
			rs = pstmt.executeQuery();
			list = new ArrayList<S_Re_CommentVo>();
			while(rs.next()) {
				S_Re_CommentVo reply = new S_Re_CommentVo();
				reply.setS_re_comm_num(rs.getInt("s_re_comm_num"));
				reply.setS_re_comm_reg(rs.getString("s_re_comm_reg"));
				reply.setS_re_comm_report(rs.getInt("s_re_comm_report"));
				reply.setS_re_comm_content(rs.getString("s_re_comm_content"));
				reply.setS_re_num(rs.getInt("s_re_num"));
				reply.setMem_num(rs.getInt("mem_num"));//작성자 회원번호
				reply.setMem_id(rs.getString("mem_id"));//작성자 아이디 (조인했기 때문에 가져올 수 있음)
				reply.setS_re_comm_reg(DurationFromNow.getTimeDiffLabel(rs.getString("s_re_comm_reg")));
				list.add(reply);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return list;
	}
}
