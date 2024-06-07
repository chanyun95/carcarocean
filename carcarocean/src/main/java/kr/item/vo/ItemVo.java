package kr.item.vo;

import kr.member.vo.MemberVo;

public class ItemVo {
	private int item_num;	
	private String item_name;
	private int item_price;
	private String item_detail;
	private String item_photo;
	private String item_reg;
	private int item_status;
	private int item_views;
	
	public int getItem_views() {
		return item_views;
	}
	public void setItem_views(int item_views) {
		this.item_views = item_views;
	}
	private MemberVo member;
	
	public int getItem_num() {
		return item_num;
	}
	public void setItem_num(int item_num) {
		this.item_num = item_num;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public int getItem_price() {
		return item_price;
	}
	public void setItem_price(int item_price) {
		this.item_price = item_price;
	}
	public String getItem_detail() {
		return item_detail;
	}
	public void setItem_detail(String item_detail) {
		this.item_detail = item_detail;
	}
	public String getItem_photo() {
		return item_photo;
	}
	public void setItem_photo(String item_photo) {
		this.item_photo = item_photo;
	}
	public String getItem_reg() {
		return item_reg;
	}
	public void setItem_reg(String item_reg) {
		this.item_reg = item_reg;
	}
	public int getItem_status() {
		return item_status;
	}
	public void setItem_status(int item_status) {
		this.item_status = item_status;
	}
	public MemberVo getMember() {
		return member;
	}
	public void setMember(MemberVo member) {
		this.member = member;
	}
	
	
}
