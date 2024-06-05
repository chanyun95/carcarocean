package kr.s_re.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import kr.s_re.vo.S_ReVo;
import kr.s_re_comment.vo.S_Re_CommentVo;
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
	         sql="INSERT INTO s_re(s_re_num,s_re_title,sell_num,s_re_content,mem_num) "
	         		+ "VALUES(s_re_seq.nextval,?,?,?,?)";
	         
	         //PreparedStatment 객체 생성
	         pstmt = conn.prepareStatement(sql);
	         
	         //?에 데이터 바인딩
	         pstmt.setString(++cnt,s_re.getS_re_title());
	         pstmt.setInt(++cnt,s_re.getSell_num());
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
	
	//회원 번호로 판매 내역을 조회
	public List<S_ReVo> sellList(int mem_num) throws Exception {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    String sql = null;
	    List<S_ReVo> list = null;
	    ResultSet rs = null;
	    try {
	        conn = DBUtil.getConnection();
	        sql = "SELECT * FROM sell JOIN member USING(mem_num) WHERE mem_num =?";
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, mem_num);
	        rs = pstmt.executeQuery();
	        list = new ArrayList<S_ReVo>();
	        while (rs.next()) {
	            S_ReVo sell = new S_ReVo();
	            sell.setSell_num(rs.getInt("sell_num"));
	            sell.setMem_num(rs.getInt("mem_num"));
	            sell.setSell_maker(rs.getString("sell_maker"));
	            sell.setSell_cname(rs.getString("sell_cname"));

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
	
		
	

	
	// 판매 후기 게시판 글 목록, 검색 글 목록
	public List<S_ReVo> getListSellReview(int start, int end, String keyfield, String keyword) throws Exception {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    String sql = null;
	    List<S_ReVo> list = null;
	    String sub_sql = "";
	    ResultSet rs = null;
	    int cnt = 0;
	    try {
	        // 커넥션풀로부터 커넥션 할당
	        conn = DBUtil.getConnection();
	        if (keyword != null && !"".equals(keyword)) {
	            // 검색 처리
	            if (keyfield.equals("1")) {
	                sub_sql += "WHERE s.s_re_title LIKE '%' || ? || '%'";
	            } else if (keyfield.equals("2")) {
	                sub_sql += "WHERE m.mem_id LIKE '%' || ? || '%'";
	            } else if (keyfield.equals("3")) {
	                sub_sql += "WHERE sell.sell_maker LIKE '%' || ? || '%'";
	            }
	        }

	        // SQL문 작성
	        sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM "
	            + "(SELECT s.s_re_num, s.s_re_title, s.sell_num, s.s_re_reg, s.s_re_modify, "
	            + "m.mem_id, sell.sell_cname, sell.sell_maker "
	            + "FROM s_re s JOIN member m ON s.mem_num = m.mem_num "
	            + "JOIN sell ON s.sell_num = sell.sell_num "
	            + sub_sql
	            + " ORDER BY s.s_re_num DESC) a) "
	            + "WHERE rnum >= ? AND rnum <= ?";

	        // PreparedStatement 객체 생성
	        pstmt = conn.prepareStatement(sql);

	        // ?에 데이터 바인딩
	        if (keyword != null && !"".equals(keyword)) {
	            pstmt.setString(++cnt, keyword);
	        }
	        pstmt.setInt(++cnt, start);
	        pstmt.setInt(++cnt, end);

	        rs = pstmt.executeQuery();
	        list = new ArrayList<S_ReVo>();
	        while (rs.next()) {
	            S_ReVo s_re = new S_ReVo();
	            s_re.setS_re_num(rs.getInt("s_re_num"));
	            s_re.setS_re_title(rs.getString("s_re_title"));
	            s_re.setSell_num(rs.getInt("sell_num"));
	            s_re.setS_re_reg(rs.getString("s_re_reg"));
	            s_re.setS_re_modify(rs.getString("s_re_modify"));
	            s_re.setMem_id(rs.getString("mem_id"));
	            s_re.setSell_cname(rs.getString("sell_cname"));
	            s_re.setSell_maker(rs.getString("sell_maker"));

	            list.add(s_re);
	        }
	    } catch (Exception e) {
	        throw new Exception(e);
	    } finally {
	        DBUtil.executeClose(rs, pstmt, conn);
	    }
	    return list;
	}
	//(메인) 판매 후기 게시판 글 목록, 검색 글 목록
		public List<S_ReVo> getListSellReviewMain(int start, int end) throws Exception {
		    Connection conn = null;
		    PreparedStatement pstmt = null;
		    String sql = null;
		    List<S_ReVo> list = null;
		  
		    ResultSet rs = null;
		    int cnt = 0;
		    try {
		        // 커넥션풀로부터 커넥션 할당
		        conn = DBUtil.getConnection();
		        

		        // SQL문 작성
		        sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM "
		        	    + "(SELECT s.s_re_num, s.s_re_title, s.s_re_content, s.sell_num, s.s_re_reg, s.s_re_modify, "
		        	    + "m.mem_id, sell.sell_cname, sell.sell_maker "
		        	    + "FROM s_re s JOIN member m ON s.mem_num = m.mem_num "
		        	    + "JOIN sell ON s.sell_num = sell.sell_num "
		        	    + " ORDER BY s.s_re_num DESC) a) "
		        	    + "WHERE rnum >= ? AND rnum <= ?";


		        // PreparedStatement 객체 생성
		        pstmt = conn.prepareStatement(sql);

		        // ?에 데이터 바인딩
		        pstmt.setInt(++cnt, start);
		        pstmt.setInt(++cnt, end);

		        rs = pstmt.executeQuery();
		        list = new ArrayList<S_ReVo>();
		        while (rs.next()) {
		            S_ReVo s_re = new S_ReVo();
		            s_re.setS_re_num(rs.getInt("s_re_num"));
		            s_re.setS_re_title(rs.getString("s_re_title"));
		            s_re.setS_re_content(rs.getString("s_re_content"));
		            s_re.setSell_num(rs.getInt("sell_num"));
		            s_re.setS_re_reg(rs.getString("s_re_reg"));
		            s_re.setS_re_modify(rs.getString("s_re_modify"));
		            s_re.setMem_id(rs.getString("mem_id"));
		            s_re.setSell_cname(rs.getString("sell_cname"));
		            s_re.setSell_maker(rs.getString("sell_maker"));

		            list.add(s_re);
		        }
		    } catch (Exception e) {
		        throw new Exception(e);
		    } finally {
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
	         sql="SELECT * FROM sell JOIN s_re r USING(sell_num) "
	         		+ "JOIN member m ON r.mem_num=m.mem_num WHERE s_re_num = ?";
	         
	         //PreparedStatment 객체 생성
	         pstmt = conn.prepareStatement(sql);
	         
	         pstmt.setInt(++cnt,s_re_num);
	         
	         rs = pstmt.executeQuery();
	         
	         if(rs.next()) {
	        	 s_Re = new S_ReVo();
	        	 s_Re.setS_re_num(rs.getInt("s_re_num"));
	        	 s_Re.setS_re_title(rs.getString("s_re_title"));
	        	 s_Re.setS_re_content(rs.getString("s_re_content"));
	        	 s_Re.setS_re_reg(rs.getString("s_re_reg"));
	        	 s_Re.setS_re_modify(rs.getString("s_re_modify"));
	        	 s_Re.setSell_num(rs.getInt("sell_num"));
	        	 s_Re.setMem_num(rs.getInt("mem_num"));
	        	 
	         }
	      }catch(Exception e) {
	         throw new Exception(e);
	      }finally {
	         DBUtil.executeClose(rs, pstmt, conn);
	      }
	      
	      return s_Re;
	}
	
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
	         sql="UPDATE s_re SET s_re_title=?,s_re_content=?,s_re_modify=SYSDATE WHERE=s_re_num=?";
	         
	         //PreparedStatment 객체 생성
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(++cnt, S_Re.getS_re_title());
	         pstmt.setString(++cnt, S_Re.getS_re_content());
	         pstmt.setInt(cnt, S_Re.getS_re_num());
	         
	         pstmt.executeUpdate();
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
	         sql="DELETE FROM s_re WHERE s_re_num=?";
	         
	         //PreparedStatment 객체 생성
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setInt(++cnt,s_re_num);
	         pstmt.executeUpdate();
	      }catch(Exception e) {
	         throw new Exception(e);
	      }finally {
	         DBUtil.executeClose(null, pstmt, conn);
	      }
	}
	
	
}
