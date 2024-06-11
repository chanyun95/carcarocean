package kr.item.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.item.vo.ItemVo;
import kr.member.vo.MemberVo;
import kr.util.DBUtil;

public class ItemDao {
	private static ItemDao dao = new ItemDao();
	private ItemDao() {};
	public static ItemDao getDao() {
		return dao;
	}
	
	public void insertItem(ItemVo item) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int cnt = 0;
		try {
			conn = DBUtil.getConnection();
			sql = "INSERT INTO ITEM (item_num,mem_num,item_name,item_price,item_detail,item_photo) VALUES (item_seq.nextval,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(++cnt, item.getMember().getMem_num());
			pstmt.setString(++cnt, item.getItem_name());
			pstmt.setInt(++cnt, item.getItem_price());
			pstmt.setString(++cnt, item.getItem_detail());
			pstmt.setString(++cnt, item.getItem_photo());
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	public void deleteItem(int item_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		String sql = null;
		int cnt = 0;
		try {
			conn = DBUtil.getConnection();
			
			sql = "DELETE FROM CHAT WHERE ITEM_NUM=?";
			pstmt1 = conn.prepareStatement(sql);
			pstmt1.setInt(1, item_num);
			pstmt1.executeUpdate();
			
			sql = "DELETE FROM ITEM WHERE ITEM_NUM=?";
			pstmt2 = conn.prepareStatement(sql);
			pstmt2.setInt(++cnt, item_num);
			pstmt2.executeUpdate();
			
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(null, pstmt1, null);
			DBUtil.executeClose(null, pstmt2, conn);
		}
	}
	public void updateItem(ItemVo item) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int cnt = 0;
		try {
			conn = DBUtil.getConnection();
			sql = "UPDATE ITEM SET item_name=?, item_price=?, item_detail=?, item_views=?, item_photo=? WHERE ITEM_NUM=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(++cnt, item.getItem_name());
			pstmt.setInt(++cnt, item.getItem_price());
			pstmt.setString(++cnt, item.getItem_detail());
			pstmt.setInt(++cnt, item.getItem_views());
			pstmt.setString(++cnt, item.getItem_photo());
			pstmt.setInt(++cnt, item.getItem_num());
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	public void updateItemStatus(int item_num, int item_status) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int cnt = 0;
		try {
			conn = DBUtil.getConnection();
			sql = "UPDATE ITEM SET item_status=? WHERE item_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(++cnt, item_status);
			pstmt.setInt(++cnt, item_num);
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}	
	public ItemVo getItem(int item_num) throws Exception{
		ItemVo item = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int cnt = 0;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM ITEM JOIN MEMBER USING (MEM_NUM) JOIN MEMBER_DETAIL USING (MEM_NUM) WHERE ITEM_NUM=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(++cnt, item_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				item = new ItemVo();
				item.setItem_num(rs.getInt("item_num"));
				item.setItem_name(rs.getString("item_name"));
				item.setItem_price(rs.getInt("item_price"));
				item.setItem_detail(rs.getString("item_detail"));
				item.setItem_photo(rs.getString("item_photo"));
				item.setItem_reg(rs.getString("item_reg"));
				item.setItem_status(rs.getInt("item_status"));
				item.setItem_views(rs.getInt("item_views"));
				MemberVo member = new MemberVo();
				member.setMem_num(rs.getInt("mem_num"));
				member.setMem_name(rs.getString("mem_name"));
				member.setMem_id(rs.getString("mem_id"));
				member.setMem_email(rs.getString("mem_email"));
				member.setMem_auth(rs.getInt("mem_auth"));
				member.setMem_grade(rs.getInt("mem_grade"));
				member.setMem_name(rs.getString("mem_name"));
				member.setMem_phone(rs.getString("mem_phone"));
				member.setMem_zipcode(rs.getString("mem_zipcode"));
				member.setMem_address1(rs.getString("mem_address1"));
				member.setMem_address2(rs.getString("mem_address2"));
				member.setMem_photo(rs.getString("mem_photo"));
				member.setMem_reg(rs.getString("mem_reg"));
				member.setMem_modify(rs.getString("mem_modify"));

				item.setMember(member);
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return item;
	}
	public int getItemListCount(String keyfield, String keyword, int item_status) throws Exception{
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String sub_sql = "";
		int cnt = 0;
		try {
			conn = DBUtil.getConnection();
			if(keyword != null && !"".equals(keyword)) {
				if(keyfield.equals("0")) sub_sql += "item_name LIKE '%' || ? || '%'";
				else if(keyfield.equals("1")) sub_sql += "item_detail LIKE '%' || ? || '%'";
				else if(keyfield.equals("2")) sub_sql += "mem_id LIKE '%' || ? || '%'";
			}
			sql = "SELECT COUNT(*) FROM ITEM JOIN MEMBER USING (MEM_NUM) JOIN MEMBER_DETAIL USING (MEM_NUM) WHERE ITEM_STATUS=? "+sub_sql;
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(++cnt, item_status);
			if(keyword != null && !"".equals(keyword)) {
				pstmt.setString(++cnt, keyword);
			}
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return count;
	}
	public List<ItemVo> getItemList(int start, int end, String keyfield, String keyword, int item_status) throws Exception{
		List<ItemVo> list = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String sub_sql = "";
		int cnt = 0;
		try {
			conn = DBUtil.getConnection();
			if(keyword != null && !"".equals(keyword)) {
				if(keyfield.equals("0")) sub_sql += "item_name LIKE '%' || ? || '%'";
				else if(keyfield.equals("1")) sub_sql += "item_detail LIKE '%' || ? || '%'";
				else if(keyfield.equals("2")) sub_sql += "mem_id LIKE '%' || ? || '%'";
			}
			sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM (SELECT * FROM ITEM JOIN MEMBER USING (MEM_NUM) JOIN MEMBER_DETAIL USING (MEM_NUM) WHERE item_status=? "+ sub_sql + " ORDER BY item_reg DESC)a) WHERE rnum >=? AND rnum <=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(++cnt, item_status);
			if(keyword != null && !"".equals(keyword)) {
				pstmt.setString(++cnt, keyword);
			}
			pstmt.setInt(++cnt, start);
			pstmt.setInt(++cnt, end);
			
			rs = pstmt.executeQuery();
			list = new ArrayList<>();
			while(rs.next()) {
				ItemVo item = new ItemVo();
				item.setItem_num(rs.getInt("item_num"));
				item.setItem_name(rs.getString("item_name"));
				item.setItem_price(rs.getInt("item_price"));
				item.setItem_detail(rs.getString("item_detail"));
				item.setItem_photo(rs.getString("item_photo"));
				item.setItem_reg(rs.getString("item_reg"));
				item.setItem_status(rs.getInt("item_status"));
				item.setItem_views(rs.getInt("item_views"));
				MemberVo member = new MemberVo();
				member.setMem_num(rs.getInt("mem_num"));
				member.setMem_name(rs.getString("mem_name"));
				member.setMem_id(rs.getString("mem_id"));
				member.setMem_email(rs.getString("mem_email"));
				member.setMem_auth(rs.getInt("mem_auth"));
				member.setMem_grade(rs.getInt("mem_grade"));
				member.setMem_name(rs.getString("mem_name"));
				member.setMem_phone(rs.getString("mem_phone"));
				member.setMem_zipcode(rs.getString("mem_zipcode"));
				member.setMem_address1(rs.getString("mem_address1"));
				member.setMem_address2(rs.getString("mem_address2"));
				member.setMem_photo(rs.getString("mem_photo"));
				member.setMem_reg(rs.getString("mem_reg"));
				member.setMem_modify(rs.getString("mem_modify"));

				item.setMember(member);
				
				list.add(item);
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
	public List<ItemVo> getItemListfavored(int start, int end, int item_status) throws Exception{
		List<ItemVo> list = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String sub_sql = "";
		int cnt = 0;
		try {
			conn = DBUtil.getConnection();
			
			sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM (SELECT * FROM ITEM JOIN MEMBER USING (MEM_NUM) JOIN MEMBER_DETAIL USING (MEM_NUM) WHERE item_status=? ORDER BY item_views DESC)a) WHERE rnum >=? AND rnum <=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(++cnt, item_status);
			pstmt.setInt(++cnt, start);
			pstmt.setInt(++cnt, end);
			
			rs = pstmt.executeQuery();
			list = new ArrayList<>();
			while(rs.next()) {
				ItemVo item = new ItemVo();
				item.setItem_num(rs.getInt("item_num"));
				item.setItem_name(rs.getString("item_name"));
				item.setItem_price(rs.getInt("item_price"));
				item.setItem_detail(rs.getString("item_detail"));
				item.setItem_photo(rs.getString("item_photo"));
				item.setItem_reg(rs.getString("item_reg"));
				item.setItem_status(rs.getInt("item_status"));
				item.setItem_views(rs.getInt("item_views"));
				MemberVo member = new MemberVo();
				member.setMem_num(rs.getInt("mem_num"));
				member.setMem_name(rs.getString("mem_name"));
				member.setMem_id(rs.getString("mem_id"));
				member.setMem_email(rs.getString("mem_email"));
				member.setMem_auth(rs.getInt("mem_auth"));
				member.setMem_grade(rs.getInt("mem_grade"));
				member.setMem_name(rs.getString("mem_name"));
				member.setMem_phone(rs.getString("mem_phone"));
				member.setMem_zipcode(rs.getString("mem_zipcode"));
				member.setMem_address1(rs.getString("mem_address1"));
				member.setMem_address2(rs.getString("mem_address2"));
				member.setMem_photo(rs.getString("mem_photo"));
				member.setMem_reg(rs.getString("mem_reg"));
				member.setMem_modify(rs.getString("mem_modify"));

				item.setMember(member);
				
				list.add(item);
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
	public void viewItem(int item_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;

		try {
			conn = DBUtil.getConnection();
			sql = "UPDATE item SET item_views=item_views+1 WHERE item_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, item_num);
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}

	}
}
