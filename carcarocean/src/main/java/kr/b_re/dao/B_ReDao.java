package kr.b_re.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import kr.b_re.vo.B_ReVo;
import kr.util.DBUtil;

public class B_ReDao {
	private static B_ReDao dao = new B_ReDao();
	public static B_ReDao getDao() {
		return dao;
	}
	private B_ReDao() {}
	
	//구매 후기 게시판 글 등록
	public void insertBuyReview(B_ReVo b_re)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int cnt = 0;
		try {
			conn = DBUtil.getConnection();
			
			sql = "INSERT INTO b_re(b_re_num,buy_num,b_re_title,b_re_content,b_re_photo) "
					+ "VALUES(b_re_seq.nextval,(SELECT buy_num FROM buy WHERE mem_num=?),?,?,?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(++cnt, b_re.getMem_num());
			pstmt.setString(++cnt, b_re.getB_re_title());
			pstmt.setString(++cnt, b_re.getB_re_content());
			pstmt.setString(++cnt, b_re.getB_re_photo());
			
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	//회원 번호로 구매 내역을 조회
	public List<B_ReVo> getBuyList(int mem_num) throws Exception {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    List<B_ReVo> list = null;
	    String sql = null;
	    try {
	        conn = DBUtil.getConnection();

	        sql = "SELECT car_num,car_name,buy_num,buy_reg FROM buy JOIN car USING(car_num) WHERE mem_num = ?";

	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, mem_num);
	        
	        rs = pstmt.executeQuery();
	        list = new ArrayList<B_ReVo>();
	        while (rs.next()) {
	            B_ReVo buy = new B_ReVo();
	            buy.setBuy_num(rs.getInt("buy_num"));
	            buy.setCar_num(rs.getInt("car_num"));
	            buy.setCar_name(rs.getString("car_name"));
	            buy.setBuy_reg(rs.getDate("buy_reg"));
	            list.add(buy);
	        }

	    } catch (Exception e) {
	        throw new Exception(e);
	    } finally {
	        DBUtil.executeClose(rs, pstmt, conn);
	    }

	    return list;
	}

	//구매 후기 게시판 총 글의 개수, 검색 개수
	
	//구매 후기 게시판 글 목록, 검색 글 목록
	
	//구매 후기 게시판 글 상세
	
	//파일 삭제
	
	//구매 후기 게시판 글 수정
	
	//구매 후기 게시판 글 삭제
	
}
