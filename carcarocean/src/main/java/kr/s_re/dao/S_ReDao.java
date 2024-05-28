package kr.s_re.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.car.vo.CarVO;
import kr.s_re.vo.S_ReVo;
import kr.util.DBUtil;

public class S_ReDao {
	//싱글턴 패턴
	private static S_ReDao instance = new S_ReDao();
	
	public static S_ReDao getDao() {
		return instance;
	}
	private S_ReDao() {}
	
	//판매 후기 게시판 글 등록
	public void insertSellReview(S_ReVo s_re) throws Exception{
		Connection conn = null;
	  	  PreparedStatement pstmt = null;
	      String sql =null;
	      int cnt = 0;
	      try {
	         //커넥션풀로부터 커넥션 할당
	         conn = DBUtil.getConnection();
	         
	         //SQL문 작성
	         sql="INSERT INTO s_re(s_re_num,s_re_title,car_num,s_re_content,mem_num) "
	         		+ "VALUES(s_re_seq.nextval,?,?,?,?)";
	         
	         //PreparedStatment 객체 생성
	         pstmt = conn.prepareStatement(sql);
	         
	         //?에 데이터 바인딩
	         pstmt.setString(++cnt,s_re.getS_re_title());
	         pstmt.setInt(++cnt,s_re.getCar_num());
	         pstmt.setString(++cnt,s_re.getS_re_content());
	         pstmt.setInt(++cnt, s_re.getMem_num());
	         //SQL문 실행   
	         pstmt.executeUpdate();
	      }catch(Exception e) {
	         throw new Exception(e);
	      }finally {
	         DBUtil.executeClose(null, pstmt, conn);
	      }
	}
	
	// sellList 메서드 수정
	public List<S_ReVo> sellList(int mem_num) throws Exception {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    String sql = null;
	    List<S_ReVo> list = null;
	    ResultSet rs = null;
	    try {
	        conn = DBUtil.getConnection();
	        sql = "SELECT car.car_num, car.car_name, s_re.s_re_num, s_re.mem_num, s_re.s_re_title, s_re.s_re_content "
	            + "FROM car LEFT JOIN s_re ON car.car_num = s_re.car_num "
	            + "WHERE s_re.mem_num = ?";
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, mem_num);
	        rs = pstmt.executeQuery();
	        list = new ArrayList<S_ReVo>();
	        while (rs.next()) {
	            S_ReVo sell = new S_ReVo();
	            sell.setS_re_num(rs.getInt("s_re_num"));
	            sell.setMem_num(rs.getInt("mem_num"));
	            sell.setS_re_title(rs.getString("s_re_title"));
	            sell.setS_re_content(rs.getString("s_re_content"));
	            // 차량 정보 설정
	            CarVO car = new CarVO();
	            car.setCar_num(rs.getInt("car_num"));
	            car.setCar_name(rs.getString("car_name"));
	            sell.setCar(car);
	            list.add(sell);
	        }
	    } catch (Exception e) {
	        throw new Exception(e);
	    } finally {
	        DBUtil.executeClose(rs, pstmt, conn);
	    }
	    return list;
	}


	
	//판매 후기 게시판 총 글의 개수, 검색 개수
	public int getSellReviewCount(String keyfield, String keyword) throws Exception{
		Connection conn = null;
	      PreparedStatement pstmt = null;
	      String sql =null;
	      ResultSet rs = null;
	      String sub_sql = "";
	      int count = 0;
	      int cnt =0;
	      try {
	         //커넥션풀로부터 커넥션 할당
	         conn = DBUtil.getConnection();
	         if(keyword !=null && !"".equals(keyword)) {
	        	 //검색처리
	        	 if(keyfield.equals("1")) sub_sql +="WHERE s_re_title LIKE '%' || ? || '%'";
				else if(keyfield.equals("2")) sub_sql +="WHERE mem_id LIKE '%' || ? || '%'";
				else if(keyfield.equals("3")) sub_sql +="WHERE s_re_content LIKE '%' || ? || '%'";
	         }
	         
	         //SQL문 작성
	         sql="SELECT count(*) FROM s_re JOIN member USING(mem_num)" +sub_sql;
	         
	         //PreparedStatment 객체 생성
	         pstmt = conn.prepareStatement(sql);
	         if(keyword!=null && !"".equals(keyword)) {
					pstmt.setString(1, keyword);
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
	
		
	

	//판매 후기 게시판 글 목록, 검색 글 목록
	public List<S_ReVo> getListSellReview(int start, int end, 
							String keyfield, String keyword) throws Exception{
		Connection conn = null;
	      PreparedStatement pstmt = null;
	      String sql =null;
	      List<S_ReVo> list = null;
	      String sub_sql = "";
	      ResultSet rs = null;
	      int cnt = 0;
	      try {
	         //커넥션풀로부터 커넥션 할당
	         conn = DBUtil.getConnection();
	         if(keyword !=null && !"".equals(keyword)) {
	        	 //검색처리
	        	 if(keyfield.equals("1")) sub_sql +="WHERE s_re_title LIKE '%' || ? || '%'";
				else if(keyfield.equals("2")) sub_sql +="WHERE mem_id LIKE '%' || ? || '%'";
				else if(keyfield.equals("3")) sub_sql +="WHERE s_re_content LIKE '%' || ? || '%'";
	         }
	         
	         //SQL문 작성
	         sql="SELECT * FROM (SELECT a.*, rownum rnum FROM "
	         		+ "(SELECT * FROM s_re JOIN member USING(mem_num) " +sub_sql
	         		+"ORDER BY s_re_num DESC)a) WHERE rnum >=? AND rnum <=?";
	         
	         //PreparedStatment 객체 생성
	         pstmt = conn.prepareStatement(sql);
	         
	         //?에 데이터 바인딩
				if(keyword!=null && !"".equals(keyword)) {
					pstmt.setString(++cnt, keyword);
				}
				pstmt.setInt(++cnt, start);
				pstmt.setInt(++cnt, end);
				
				rs = pstmt.executeQuery();
				list = new ArrayList<S_ReVo>();
				while(rs.next()) {
					S_ReVo s_re = new S_ReVo();
					s_re.setS_re_num(rs.getInt("s_re_num"));
					s_re.setS_re_title(rs.getString("s_re_title"));
					s_re.setCar_num(rs.getInt("car_num"));
					s_re.setS_re_reg(rs.getString("s_re_reg"));
					s_re.setS_re_modify(rs.getString("s_re_modify"));
					s_re.setMem_id(rs.getString("mem_id"));
					
					list.add(s_re);
				}
	      }catch(Exception e) {
	         throw new Exception(e);
	      }finally {
	         DBUtil.executeClose(rs, pstmt, conn);
	      }
		return list;
	}
	//판매 후기 게시판 글 상세
	public S_ReVo getSellReview(int s_re_num)throws Exception{
		Connection conn = null;
	      PreparedStatement pstmt = null;
	      String sql =null;
	      ResultSet rs = null;
	      S_ReVo s_Re = null;
	      int cnt = 0;
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
	         DBUtil.executeClose(rs, pstmt, conn);
	      }
	      
	      return s_Re;
	}
	//파일 삭제
	
	//판매 후기 게시판 글 수정
	public void updateSellReview(S_ReVo S_Re)throws Exception{
		Connection conn = null;
	      PreparedStatement pstmt = null;
	      String sql =null;
	      int cnt = 0;
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
		
	}
	//판매 후기 게시판 글 삭제
	public void deleteSellReview(int s_re_num)throws Exception{
		Connection conn = null;
	      PreparedStatement pstmt = null;
	      String sql =null;
	      int cnt = 0;
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
	}
	
}
