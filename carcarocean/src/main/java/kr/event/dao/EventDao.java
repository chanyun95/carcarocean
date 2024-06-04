package kr.event.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.event.vo.EventVo;
import kr.util.DBUtil;
import kr.util.StringUtil;

public class EventDao {
	//싱글턴 패턴
	private static EventDao instance = new EventDao();
	
	public static EventDao getDao() {
		return instance;
	}
	private EventDao() {}
	
	//글 등록
	public void insertEvent(EventVo event)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int cnt = 0;
		try {
			//커넥션풀로 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "INSERT INTO event (event_num, event_title, event_content, event_photo)"
					+ " VALUES (event_seq.nextval, ?, ?, ?)";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setString(++cnt, event.getEvent_title());
			pstmt.setString(++cnt, event.getEvent_content());
			pstmt.setString(++cnt, event.getEvent_photo());
			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//총 글의 개수, 검색 개수
	public int getEventCount(String keyfield, String keyword)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String sub_sql = "";
		int count = 0;
		int cnt = 0;
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			
			if(keyword != null && !"".equals(keyword)) {
				//검색 처리
				if(keyfield.equals("1")) sub_sql += "WHERE event_title Like '%' || ? || '%'";
				else if(keyfield.equals("2")) sub_sql += "WHERE event_content Like '%' || ? || '%'";
			}
			//SQL문 작성
			sql = "SELECT COUNT(*) FROM event " + sub_sql;
			////PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			if(keyword != null && !"".equals(keyword)) {
				pstmt.setString(++cnt, keyword);
			}
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
	
	//글 목록
	public List<EventVo> getListEvent(int start, int end, String keyfield, String keyword)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<EventVo> list = null;
		String sql = null;
		String sub_sql = "";
		int cnt = 0;
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			
			if(keyword != null && !"".equals(keyword)) {
				//검색 처리
				if(keyfield.equals("1")) sub_sql += "WHERE event_title Like '%' || ? || '%'";
				else if(keyfield.equals("2")) sub_sql += "WHERE event_content Like '%' || ? || '%'";
			}
			//SQL문 작성
			sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM (SELECT * FROM event "
			+ sub_sql +" ORDER BY event_num DESC)a) WHERE rnum >=? AND rnum <=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			if(keyword != null && !"".equals(keyword)) {
				pstmt.setString(++cnt, keyword);
			}
			pstmt.setInt(++cnt, start);
			pstmt.setInt(++cnt, end);
			//SQL문 실행
			rs = pstmt.executeQuery();
			list = new ArrayList<EventVo>();
			while(rs.next()) {
				EventVo event = new EventVo();
				event.setEvent_num(rs.getInt("event_num"));
				event.setEvent_title(StringUtil.useNoHTML(rs.getString("event_title")));
				event.setEvent_reg(rs.getString("event_reg"));
				event.setEvent_modify(rs.getString("event_modify"));
				list.add(event);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
	
	//글 상세
	public EventVo detailEvent(int event_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		EventVo event = null;
		String sql = null;
		int cnt = 0;
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT * FROM event WHERE event_num=?";
			//PreparedStatement 객체생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(++cnt, event_num);
			//SQL문 실행
			rs = pstmt.executeQuery();
			if(rs.next()) {
				event = new EventVo();
				event.setEvent_num(rs.getInt("event_num"));
				event.setEvent_title(rs.getString("event_title"));
				event.setEvent_content(rs.getString("event_content"));
				event.setEvent_reg(rs.getString("event_reg"));
				event.setEvent_modify(rs.getString("event_modify"));
				event.setEvent_photo(rs.getString("event_photo"));
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return event;
	}
	//파일 삭제
	public void deletePhoto(int event_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int cnt = 0;
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "UPDATE event SET event_photo='' WHERE event_num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(++cnt, event_num);
			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	//글 수정
	public void updateEvent(EventVo event)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		String sub_sql = "";
		int cnt = 0;
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			
			if(event.getEvent_photo() != null && !"".equals(event.getEvent_photo())) {
				sub_sql += ",event_photo=?";
			}
			//SQL문 작성
			sql = "UPDATE event SET event_title=?, event_content=?, event_modify=SYSDATE" + sub_sql
					+ " WHERE event_num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setString(++cnt, event.getEvent_title());
			pstmt.setString(++cnt, event.getEvent_content());
			
			if(event.getEvent_photo() != null && !"".equals(event.getEvent_photo())) {
				pstmt.setString(++cnt, event.getEvent_photo());
			}
			pstmt.setInt(++cnt, event.getEvent_num());
			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//글 삭제
	public void deleteEvent(int event_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int cnt = 0;
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "DELETE FROM event WHERE event_num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(++cnt, event_num);
			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
}
