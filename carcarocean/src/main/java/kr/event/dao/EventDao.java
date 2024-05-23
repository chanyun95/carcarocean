package kr.event.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import kr.event.vo.EventVo;
import kr.util.DBUtil;

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
		
		return event;
	}
	
	//글 수정
	public void updateEvent(EventVo event)throws Exception{
		
	}
	
	//글 삭제
	public void deleteEvent(int event_num)throws Exception{
		
	}
}
