package kr.b_re.vo;

import java.sql.Date;

public class B_ReVo {
	private int b_re_num;
	private int buy_num;
	private String b_re_title;
	private String b_re_content;
	private String b_re_photo;
	private String b_re_reg;
	private String b_re_modify;
	
	private Date buy_reg;
	private int car_num;
	private int mem_num;
	
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public int getCar_num() {
		return car_num;
	}
	public void setCar_num(int car_num) {
		this.car_num = car_num;
	}
	public String getCar_name() {
		return car_name;
	}
	public void setCar_name(String car_name) {
		this.car_name = car_name;
	}
	private String car_name;
	

	public Date getBuy_reg() {
		return buy_reg;
	}
	public void setBuy_reg(Date buy_reg) {
		this.buy_reg = buy_reg;
	}
	public int getB_re_num() {
		return b_re_num;
	}
	public void setB_re_num(int b_re_num) {
		this.b_re_num = b_re_num;
	}
	public int getBuy_num() {
		return buy_num;
	}
	public void setBuy_num(int buy_num) {
		this.buy_num = buy_num;
	}
	public String getB_re_title() {
		return b_re_title;
	}
	public void setB_re_title(String b_re_title) {
		this.b_re_title = b_re_title;
	}
	public String getB_re_content() {
		return b_re_content;
	}
	public void setB_re_content(String b_re_content) {
		this.b_re_content = b_re_content;
	}
	public String getB_re_photo() {
		return b_re_photo;
	}
	public void setB_re_photo(String b_re_photo) {
		this.b_re_photo = b_re_photo;
	}
	public String getB_re_reg() {
		return b_re_reg;
	}
	public void setB_re_reg(String b_re_reg) {
		this.b_re_reg = b_re_reg;
	}
	public String getB_re_modify() {
		return b_re_modify;
	}
	public void setB_re_modify(String b_re_modify) {
		this.b_re_modify = b_re_modify;
	}
	
	
}
