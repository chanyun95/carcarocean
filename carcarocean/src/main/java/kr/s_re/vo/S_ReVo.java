package kr.s_re.vo;

import kr.car.vo.CarVO;

public class S_ReVo {
	private int s_re_num;
	private int car_num;
	private int mem_num;
	private String s_re_title;
	private String s_re_content;
	private String s_re_reg;
	private String s_re_modify;	
	
	private CarVO car;
	private String car_name;
	
	private String mem_id;			//회원아이디
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getCar_name() {
		return car_name;
	}
	public void setCar_name(String car_name) {
		this.car_name = car_name;
	}
	public CarVO getCar() {
		return car;
	}
	public void setCar(CarVO car) {
		this.car = car;
	}
	public int getS_re_num() {
		return s_re_num;
	}
	public void setS_re_num(int s_re_num) {
		this.s_re_num = s_re_num;
	}
	public int getCar_num() {
		return car_num;
	}
	public void setCar_num(int car_num) {
		this.car_num = car_num;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public String getS_re_title() {
		return s_re_title;
	}
	public void setS_re_title(String s_re_title) {
		this.s_re_title = s_re_title;
	}
	public String getS_re_content() {
		return s_re_content;
	}
	public void setS_re_content(String s_re_content) {
		this.s_re_content = s_re_content;
	}
	public String getS_re_reg() {
		return s_re_reg;
	}
	public void setS_re_reg(String s_re_reg) {
		this.s_re_reg = s_re_reg;
	}
	public String getS_re_modify() {
		return s_re_modify;
	}
	public void setS_re_modify(String s_re_modify) {
		this.s_re_modify = s_re_modify;
	}
	
}
