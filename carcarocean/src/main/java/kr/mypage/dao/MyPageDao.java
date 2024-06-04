package kr.mypage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import kr.b_re.vo.B_ReVo;
import kr.board.vo.BoardVo;
import kr.buy.vo.BuyVo;
import kr.favorite_car.vo.Favorite_carVo;
import kr.member.dao.MemberDao;
import kr.member.vo.MemberVo;
import kr.qa.vo.QaVo;
import kr.s_re.vo.S_ReVo;
import kr.sell.vo.SellVo;
import kr.util.DBUtil;
import kr.util.ShopUtil;

public class MyPageDao {
	//싱글톤
	private static MyPageDao dao = new MyPageDao();
	public static MyPageDao getDao() {
		return dao;
	}
	private MyPageDao() {};
	
	//판매현황
	public static List<SellVo> getSellCurrent(int mem_num)throws Exception{
		List<SellVo> list = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int cnt = 0;
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			//화면 가독성 위해 최근 수정일기준 10개의 데이터만 표시
			sql = "SELECT * FROM (SELECT sell_num, sell_cname, sell_check, sell_modify_check FROM sell WHERE mem_num = ? "
                    + "ORDER BY sell_modify_check DESC) WHERE ROWNUM <= 10";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(++cnt, mem_num);
			//SQL문 실행
			rs = pstmt.executeQuery();
			list = new ArrayList<>();
			while(rs.next()) {
				SellVo sell = new SellVo();
				sell.setSell_num(rs.getInt("sell_num"));
				sell.setSell_cname(rs.getString("sell_cname"));
				sell.setSell_check(rs.getInt("sell_check"));
				sell.setSell_modify_check(rs.getString("sell_modify_check"));
				list.add(sell);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
	
	//거래내역
	public static List<SellVo> myTrade(int mem_num)throws Exception{
		List<SellVo> list = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int cnt = 0;
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			//화면 가독성 위해 최근 수정일기준 10개의 데이터만 표시
			sql = "SELECT * FROM (SELECT sell_num, sell_cname, sell_check, sell_modify_check FROM sell WHERE mem_num = ? "
                    + "ORDER BY sell_modify_check DESC) WHERE ROWNUM <= 10";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(++cnt, mem_num);
			//SQL문 실행
			rs = pstmt.executeQuery();
			list = new ArrayList<>();
			while(rs.next()) {
				SellVo sell = new SellVo();
				sell.setSell_num(rs.getInt("sell_num"));
				sell.setSell_cname(rs.getString("sell_cname"));
				sell.setSell_check(rs.getInt("sell_check"));
				sell.setSell_modify_check(rs.getString("sell_modify_check"));
				list.add(sell);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
	//문의내역
	public static List<QaVo> MyQna(int mem_num)throws Exception{
		List<QaVo> list = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int cnt = 0;
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			//화면 가독성 위해 최근 수정일기준 10개의 데이터만 표시
			sql = "SELECT * FROM (SELECT qa_num,qa_title,qa_reg,qa_modify FROM qa where mem_num = ?"
					+ "ORDER BY qa_reg DESC) WHERE ROWNUM <= 10";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(++cnt, mem_num);
			//SQL문 실행
			rs = pstmt.executeQuery();
			list = new ArrayList<>();
			while(rs.next()) {
				QaVo qa = new QaVo();
				qa.setQa_num(rs.getInt("qa_num"));
				qa.setQa_title(rs.getString("qa_title"));
				qa.setQa_reg(rs.getString("qa_reg"));
				qa.setQa_modify(rs.getString("qa_modify"));
				list.add(qa);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
		
	}
	//내가 쓴 자유게시판 글
	public static List<BoardVo> MyWrite(int mem_num)throws Exception{
		List<BoardVo> list = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int cnt = 0;
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			//화면 가독성 위해 최근 수정일기준 10개의 데이터만 표시
			sql = "SELECT * FROM (SELECT board_num,board_title,board_reg,board_modify FROM "
					+ "board WHERE mem_num =? ORDER BY board_reg DESC) WHERE ROWNUM <= 10";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(++cnt, mem_num);
			//SQL문 실행
			rs = pstmt.executeQuery();
			list = new ArrayList<>();
			while(rs.next()) {
				BoardVo board = new BoardVo();
				board.setBoard_num(rs.getInt("board_num"));
				board.setBoard_title(rs.getString("board_title"));
				board.setBoard_reg(rs.getString("board_reg"));
				board.setBoard_modify(rs.getString("board_modify"));
				list.add(board);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
	//내가쓴글 판매후기
		public static List<S_ReVo> MyS_Re(int mem_num)throws Exception{
			List<S_ReVo> slist = null;
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
			int cnt = 0;
			try {
				//커넥션풀로부터 커넥션 할당
				conn = DBUtil.getConnection();
				//SQL문 작성
				//화면 가독성 위해 최근 수정일기준 10개의 데이터만 표시
				sql = "SELECT * FROM (SELECT s_re_num,s_re_title,s_re_reg,s_re_modify FROM s_re where mem_num = ?"
						+ "ORDER BY s_re_reg DESC) WHERE ROWNUM <= 10";;
				//PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				//?에 데이터 바인딩
				pstmt.setInt(++cnt, mem_num);
				//SQL문 실행
				rs = pstmt.executeQuery();
				slist = new ArrayList<>();
				while(rs.next()) {
					S_ReVo sre = new S_ReVo();
					sre.setS_re_num(rs.getInt("s_re_num"));
					sre.setS_re_title(rs.getString("s_re_title"));
					sre.setS_re_reg(rs.getString("s_re_reg"));
					sre.setS_re_modify(rs.getString("s_re_modify"));
					slist.add(sre);
				}
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(rs, pstmt, conn);
			}
			return slist;
		}
		//내가쓴글 구매후기
		public static List<B_ReVo> MyB_Re(int mem_num)throws Exception{
			List<B_ReVo> blist = null;
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
			int cnt = 0;
			try {
				//커넥션풀로부터 커넥션 할당
				conn = DBUtil.getConnection();
				//SQL문 작성
				//화면 가독성 위해 최근 수정일기준 10개의 데이터만 표시
				sql = "SELECT * FROM (SELECT b_re_num,b_re_title,b_re_reg,b_re_modify FROM b_re JOIN buy ON b_re.buy_num = buy.buy_num"
						+ " WHERE buy.mem_num = ?"
						+ "ORDER BY b_re_reg DESC) WHERE ROWNUM <=  10";
				//PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				//?에 데이터 바인딩
				pstmt.setInt(++cnt, mem_num);
				//SQL문 실행
				rs = pstmt.executeQuery();
				blist = new ArrayList<>();
				while(rs.next()) {
					B_ReVo bre = new B_ReVo();
					bre.setB_re_num(rs.getInt("b_re_num"));
					bre.setB_re_title(rs.getString("b_re_title"));
					bre.setB_re_reg(rs.getString("b_re_reg"));
					bre.setB_re_modify(rs.getString("b_re_modify"));
					blist.add(bre);
				}
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(rs, pstmt, conn);
			}
			return blist;
		}
		//판매내역
		public static List<SellVo> mySell(int mem_num)throws Exception{
			List<SellVo> list = null;
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
			int cnt = 0;
			try {
				//커넥션풀로부터 커넥션 할당
				conn = DBUtil.getConnection();
				//SQL문 작성
				//화면 가독성 위해 최근 수정일기준 10개의 데이터만 표시
				sql = "SELECT * FROM (SELECT sell_num, sell_cname, sell_reg, sell_modify_check FROM sell WHERE mem_num = ? AND sell_check=2 "
	                    + "ORDER BY sell_modify_check DESC) WHERE ROWNUM <= 10";
				//PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				//?에 데이터 바인딩
				pstmt.setInt(++cnt, mem_num);
				//SQL문 실행
				rs = pstmt.executeQuery();
				list = new ArrayList<>();
				while(rs.next()) {
					SellVo sell = new SellVo();
					sell.setSell_num(rs.getInt("sell_num"));
					sell.setSell_cname(rs.getString("sell_cname"));
					sell.setSell_reg(rs.getString("sell_reg"));
					sell.setSell_modify_check(rs.getString("sell_modify_check"));
					list.add(sell);
				}
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(rs, pstmt, conn);
			}
			return list;
		}
		//구매내역
		public static List<BuyVo> myBuy(int mem_num)throws Exception{
			List<BuyVo> list = null;
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
			int cnt = 0;
			try {
				//커넥션풀로부터 커넥션 할당
				conn = DBUtil.getConnection();
				//SQL문 작성
				//화면 가독성 위해 최근 수정일기준 10개의 데이터만 표시
				sql = "SELECT * FROM (SELECT buy.buy_num,car.car_name,car.car_price,buy.buy_reg,mem_grade FROM "
						+ "buy JOIN car ON buy.car_num = car.car_num LEFT OUTER JOIN member_detail d ON buy.mem_num=d.mem_num WHERE buy.mem_num = ? "
						+ "ORDER BY buy.buy_reg DESC) WHERE ROWNUM <=  10";
				//PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				//?에 데이터 바인딩
				pstmt.setInt(++cnt, mem_num);
				//SQL문 실행
				rs = pstmt.executeQuery();
				list = new ArrayList<>();
				while(rs.next()) {
					BuyVo buy = new BuyVo(); 
					buy.setBuy_num(rs.getInt("buy_num"));
					buy.setCar_name(rs.getString("car_name"));
					buy.setCar_price(ShopUtil.getDiscount(rs.getInt("mem_grade"),rs.getInt("car_price")));
					buy.setBuy_reg(rs.getString("buy_reg"));
					list.add(buy);
				}
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(rs, pstmt, conn);
			}
			return list;
		}
		//검수중인 항목 숫자

}