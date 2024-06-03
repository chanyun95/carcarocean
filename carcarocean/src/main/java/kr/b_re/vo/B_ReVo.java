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
	
	private int mem_num;
	//차량의 차명,차 사진,제조사,주행거리,차량번호,가격
	private int car_num;
	private String car_name;
	private String car_photo;
	private String car_maker;
	private int car_mile;
	private String car_cnumber;
	private int car_price;
	//검수자 이름,사진
	private String checker_name;
	private String checker_photo;
	
	public int getCar_price() {
		return car_price;
	}
	public void setCar_price(int car_price) {
		this.car_price = car_price;
	}
	public String getChecker_name() {
		return checker_name;
	}
	public void setChecker_name(String checker_name) {
		this.checker_name = checker_name;
	}
	public String getChecker_photo() {
		return checker_photo;
	}
	public void setChecker_photo(String checker_photo) {
		this.checker_photo = checker_photo;
	}
	public String getCar_maker() {
		return car_maker;
	}
	public void setCar_maker(String car_maker) {
		this.car_maker = car_maker;
	}
	public int getCar_mile() {
		return car_mile;
	}
	public void setCar_mile(int car_mile) {
		this.car_mile = car_mile;
	}
	public String getCar_cnumber() {
		return car_cnumber;
	}
	public void setCar_cnumber(String car_cnumber) {
		this.car_cnumber = car_cnumber;
	}
	public String getCar_photo() {
		return car_photo;
	}
	public void setCar_photo(String car_photo) {
		this.car_photo = car_photo;
	}
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
