package kr.notice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import kr.notice.vo.NoticeVo;
import kr.util.DBUtil;

public class NoticeDao {
	private static NoticeDao dao = new NoticeDao();
	public static NoticeDao getDao() {
		return dao;
	}
	private NoticeDao() {};
	
	//글 등록
	public void insertNotice(NoticeVo vo)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int cnt = 0;
		try {
			conn = DBUtil.getConnection();
			
			sql = "INSERT INTO notice (notice_num,notice_title,notice_content,notice_photo,notice_impt,notice_hit) VALUES("
					+ "notice_seq.nextval,?,?,?,?,?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(++cnt, vo.getNotice_title());
			pstmt.setString(++cnt, vo.getNotice_content());
			pstmt.setString(++cnt, vo.getNotice_photo());
			pstmt.setInt(++cnt, vo.getNotice_impt());
			pstmt.setInt(++cnt, vo.getNotice_hit());
			
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	//중요도 체크
	public void insertImpt(NoticeVo vo)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql =null;
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
         
			//SQL문 작성
			sql="INSERT INTO notice ";
         
			//PreparedStatment 객체 생성
			pstmt = conn.prepareStatement(sql);
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	//총 글의 개수,검색 개수
	
	//글 목록, 검색 글 목록
	public List<NoticeVo> getListNotice(int start,int end,String keyfield,String keyword)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql =null;
		String sub_sql = null;
		List<NoticeVo> list = null;
		int cnt = 0;
		try {
			conn = DBUtil.getConnection();

			sql="";
         
			pstmt = conn.prepareStatement(sql);
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
		return list;
	}
	//글 상세
	public NoticeVo getNotice(int notice_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		NoticeVo vo = null;
		String sql = null;
		int cnt = 0;
		try {
			conn = DBUtil.getConnection();
			
			sql = "SELECT * FROM notice WHERE notice_num=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(++cnt, notice_num);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				vo = new NoticeVo();
				vo.setNotice_num(rs.getInt("notice_num"));
				vo.setNotice_title(rs.getString("notice_title"));
				vo.setNotice_content(rs.getString("notice_content"));
				vo.setNotice_photo(rs.getString("notice_photo"));
				vo.setNotice_impt(rs.getInt("notice_impt"));
				vo.setNotice_hit(rs.getInt("notice_hit"));
				vo.setNotice_reg(rs.getString("notice_reg"));
				vo.setNotice_modify(rs.getString("notice_modify"));
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return vo;
	}
	//조회수 증가
	//파일 삭제
	//글 수정
	//글 삭제
}
