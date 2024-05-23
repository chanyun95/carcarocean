package kr.qa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import kr.qa.vo.QaVo;
import kr.util.DBUtil;

public class QaDao {
	//싱글턴 패턴
	private static QaDao instance = new QaDao();
	
	public static QaDao getDao() {
		return instance;
	}
	private QaDao() {}
	
	//글 등록
	public void insertQa(QaVo qa)throws Exception{
		
	}
	
	//총 글의 개수, 검색 개수
	public int getQaCount(String keyfield, String keyword)throws Exception{
		  Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      String sql = null;
	      String sub_sql = "";
	      int count = 0;
	      
	      try {
	         //커넥션풀로부터 커넥션 할당
	         conn = DBUtil.getConnection();
	         
	         //SQL문 작성
	         sql="";
	         
	         //PreparedStatment 객체 생성
	         pstmt = conn.prepareStatement(sql);
	      }catch(Exception e) {
	         throw new Exception(e);
	      }finally {
	         DBUtil.executeClose(null, pstmt, conn);
	      }
	      return count;
	}
	//글 목록
	public List<QaVo> getListQa(int start, int end, String keyfield, String keyword)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<QaVo> list = null;
		String sql = null;
		String sub_sql = "";
		int cnt = 0;
		
		return list;
	}
	
	//글 상세
	public QaVo detailQa(int qa_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		QaVo qa = null;
		String sql = null;
		
		return qa;
	}	
	
	//글 수정
	public void updateQa(QaVo qa)throws Exception{
		
	}
	
	//글 삭제
	public void deleteQa(int qa_num)throws Exception{
		
	}
}
