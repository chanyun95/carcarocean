package kr.sell.vo;

public class SellVo {
	private int sell_num;			// 판매 식별자
	private int sell_mile;			// 주행거리
	private String sell_cnumber;	// 차량번호
	private String sell_maker;		// 제조사
	private String sell_cname;		// 차명
	private String sell_name;		// 신청자 이름
	private String sell_phone;		// 전화번호
	private String sell_place1;		// 검수 희망 지역(시/도)
	private String sell_place2;		// 검수 희망 장소(군/구)
	private int sell_date;			// 검수 희망일(0:즉시, 1:7일이내, 2:30일이내)
	private int sell_check;			// 검수 완료여부(0:미완료, 1:진행중, 2:완료) Default 0
	private String sell_modify_check;
	private String sell_reg;		// 판매 정보 등록일 Default SYSDATE
	private String sell_modify;		// 판매 정보 수정일  
	private int mem_num;			// 회원 식별자	
	
	public int getSell_num() {
		return sell_num;
	}
	public void setSell_num(int sell_num) {
		this.sell_num = sell_num;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public int getSell_mile() {
		return sell_mile;
	}
	public void setSell_mile(int sell_mile) {
		this.sell_mile = sell_mile;
	}
	public String getSell_cnumber() {
		return sell_cnumber;
	}
	public void setSell_cnumber(String sell_cnumber) {
		this.sell_cnumber = sell_cnumber;
	}
	public String getSell_maker() {
		return sell_maker;
	}
	public void setSell_maker(String sell_maker) {
		this.sell_maker = sell_maker;
	}
	public String getSell_cname() {
		return sell_cname;
	}
	public void setSell_cname(String sell_cname) {
		this.sell_cname = sell_cname;
	}
	public String getSell_name() {
		return sell_name;
	}
	public void setSell_name(String sell_name) {
		this.sell_name = sell_name;
	}
	public String getSell_phone() {
		return sell_phone;
	}
	public void setSell_phone(String sell_phone) {
		this.sell_phone = sell_phone;
	}
	public String getSell_place1() {
		return sell_place1;
	}
	public void setSell_place1(String sell_place1) {
		this.sell_place1 = sell_place1;
	}
	public String getSell_place2() {
		return sell_place2;
	}
	public void setSell_place2(String sell_place2) {
		this.sell_place2 = sell_place2;
	}
	public int getSell_date() {
		return sell_date;
	}
	public void setSell_date(int sell_date) {
		this.sell_date = sell_date;
	}
	public int getSell_check() {
		return sell_check;
	}
	public void setSell_check(int sell_check) {
		this.sell_check = sell_check;
	}
	public String getSell_modify_check() {
		return sell_modify_check;
	}
	public void setSell_modify_check(String sell_modify_check) {
		this.sell_modify_check = sell_modify_check;
	}
	public String getSell_reg() {
		return sell_reg;
	}
	public void setSell_reg(String sell_reg) {
		this.sell_reg = sell_reg;
	}
	public String getSell_modify() {
		return sell_modify;
	}
	public void setSell_modify(String sell_modify) {
		this.sell_modify = sell_modify;
	}
	
	
}
