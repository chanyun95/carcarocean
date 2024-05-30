package kr.notice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.notice.vo.NoticeVo;
import kr.util.DBUtil;
import kr.util.StringUtil;

public class NoticeDao {
	private static NoticeDao dao = new NoticeDao();
	public static NoticeDao getDao() {
		return dao;
	}
	private NoticeDao() {};

	//공지사항 글 등록
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
	//공지사항 총 글의 개수,검색 개수
	public int getNoticeCount(String keyfield,String keyword)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		int cnt = 0;
		String sql = null;
		String sub_sql = "";
	
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();

			if(keyword!=null && !"".equals(keyword)) {
				if(keyfield.equals("1")) sub_sql += "WHERE notice_title LIKE '%' || ? || '%'";
				else if(keyfield.equals("2")) sub_sql += "WHERE notice_content LIKE '%' || ? || '%'";
			}
			//SQL문 작성
			sql="SELECT COUNT(*) FROM notice "+sub_sql;

			//PreparedStatment 객체 생성
			pstmt = conn.prepareStatement(sql);
			if(keyword!=null && !"".equals(keyword)) {
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
	//공지사항 글 목록, 검색 글 목록
	public List<NoticeVo> getListNotice(int start,int end,String keyfield,String keyword)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql =null;
		String sub_sql = "";
		List<NoticeVo> list = null;
		int cnt = 0;
		try {
			conn = DBUtil.getConnection();
			//검색
			if(keyword!=null && !"".equals(keyword)) {
				if(keyfield.equals("1")) sub_sql += "WHERE notice_title LIKE '%' || ? || '%'";
				else if(keyfield.equals("2")) sub_sql += "WHERE notice_content LIKE '%' || ? || '%'";
			}
			sql="SELECT * FROM (SELECT a.*, rownum rnum FROM (SELECT * FROM notice " + sub_sql +" ORDER BY "
					+ "notice_impt DESC,notice_num DESC)a) WHERE rnum >= ? AND rnum <= ?";

			pstmt = conn.prepareStatement(sql);
			if(keyword!=null && !"".equals(keyword)) {
				pstmt.setString(++cnt, keyword);
			}
			pstmt.setInt(++cnt, start);
			pstmt.setInt(++cnt, end);

			rs = pstmt.executeQuery();
			list = new ArrayList<NoticeVo>();
			while(rs.next()) {
				NoticeVo notice = new NoticeVo();
				notice.setNotice_num(rs.getInt("notice_num"));
				notice.setNotice_title(StringUtil.useNoHTML(rs.getString("notice_title")));
				notice.setNotice_reg(rs.getString("notice_reg"));
				notice.setNotice_hit(rs.getInt("notice_hit"));
				notice.setNotice_impt(rs.getInt("notice_impt"));
				list.add(notice);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
	//공지사항 글 상세
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
	//파일 삭제
	public void deleteFile(int notice_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int cnt = 0;
		try {
			//커넥션풀로부터 커넥션 할당
	        conn = DBUtil.getConnection();
	         
	        //SQL문 작성
	        sql="UPDATE notice SET notice_photo='' WHERE notice_num=?";
	         
         //PreparedStatment 객체 생성
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(++cnt, notice_num);
	        
	        pstmt.executeUpdate();
	        
	     }catch(Exception e) {
	        throw new Exception(e);
	     }finally {
	        DBUtil.executeClose(null, pstmt, conn);
	     }
	}
	//공지사항 글 수정
	public void updateNotice(NoticeVo notice)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		String sub_sql = "";
		int cnt = 0;
		try {
         //커넥션풀로부터 커넥션 할당
         conn = DBUtil.getConnection();
         //SQL문 작성
         if(notice.getNotice_photo()!=null && !"".equals(notice.getNotice_photo())) {
        	 sub_sql += ",notice_photo=?";
         }
         sql="UPDATE notice SET notice_title=?,notice_content=?,notice_modify=SYSDATE"+ sub_sql +",notice_impt=? WHERE notice_num=?";
         
         //PreparedStatment 객체 생성
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(++cnt, notice.getNotice_title());
         pstmt.setString(++cnt, notice.getNotice_content());
         if(notice.getNotice_photo()!=null && !"".equals(notice.getNotice_photo())) {
        	 pstmt.setString(++cnt, notice.getNotice_photo());
         }
         pstmt.setInt(++cnt, notice.getNotice_impt());
         pstmt.setInt(++cnt, notice.getNotice_num());
         
         pstmt.executeUpdate();
         
	     }catch(Exception e) {
	        throw new Exception(e);
	     }finally {
	        DBUtil.executeClose(null, pstmt, conn);
	     }
	}
	//공지사항 조회수
	public void updateReadCount(int notice_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int cnt = 0;
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "UPDATE notice SET notice_hit=notice_hit+1 WHERE notice_num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(++cnt, notice_num);
			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
}
