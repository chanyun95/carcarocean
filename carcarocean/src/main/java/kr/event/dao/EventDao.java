package kr.event.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import kr.event.vo.EventVo;

public class EventDao {
	//싱글턴 패턴
	private static EventDao instance = new EventDao();
	
	public static EventDao getDao() {
		return instance;
	}
	private EventDao() {}
	
	//글 등록
	public void insertEvent(EventVo event)throws Exception{
		
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
