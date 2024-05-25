package kr.car.vo;

import java.sql.Date;

public class CarVO {
	private int car_num;					//차량 식별 번호
	private String car_maker;			//제조사
	private String car_name;			//차명
	private int car_size;					//차종
	private String car_birth;				//연식
	private int car_cc;						//배기량
	private int car_fuel_type;			//연료타입
	private float car_fuel_efficiency;	//연비
	private int car_mile;					//주행거리
	private int car_price;					//가격
	private String car_color;				//색상
	private String car_photo;			//사진URL
	private int car_auto;					//변속기
	private int car_use;					//사용기간
	private String car_accident;		//사고이력
	private int car_owner_change;	//소유자 변경 횟수
	private String car_design_op;	//디자인옵션
	private String car_con_op;			//편의옵션
	private String car_drive_op;		//주행옵션
	private int checker_num;			//검수자 식별 번호
	private int car_status;				//차량 판매 가능 여부 0:판매중, 1:판매완료
	private String car_checker_opinion;
	
	public int getCar_num() {
		return car_num;
	}
	public void setCar_num(int car_num) {
		this.car_num = car_num;
	}
	public String getCar_maker() {
		return car_maker;
	}
	public void setCar_maker(String car_maker) {
		this.car_maker = car_maker;
	}
	public String getCar_name() {
		return car_name;
	}
	public void setCar_name(String car_name) {
		this.car_name = car_name;
	}
	public int getCar_size() {
		return car_size;
	}
	public void setCar_size(int car_size) {
		this.car_size = car_size;
	}
	public String getCar_birth() {
		return car_birth;
	}
	public void setCar_birth(String car_birth) {
		this.car_birth = car_birth;
	}
	public int getCar_cc() {
		return car_cc;
	}
	public void setCar_cc(int car_cc) {
		this.car_cc = car_cc;
	}
	public int getCar_fuel_type() {
		return car_fuel_type;
	}
	public void setCar_fuel_type(int car_fuel_type) {
		this.car_fuel_type = car_fuel_type;
	}
	public float getCar_fuel_efficiency() {
		return car_fuel_efficiency;
	}
	public void setCar_fuel_efficiency(float car_fuel_efficiency) {
		this.car_fuel_efficiency = car_fuel_efficiency;
	}
	public int getCar_mile() {
		return car_mile;
	}
	public void setCar_mile(int car_mile) {
		this.car_mile = car_mile;
	}
	public int getCar_price() {
		return car_price;
	}
	public void setCar_price(int car_price) {
		this.car_price = car_price;
	}
	public String getCar_color() {
		return car_color;
	}
	public void setCar_color(String car_color) {
		this.car_color = car_color;
	}
	public String getCar_photo() {
		return car_photo;
	}
	public void setCar_photo(String car_photo) {
		this.car_photo = car_photo;
	}
	public int getCar_auto() {
		return car_auto;
	}
	public void setCar_auto(int car_auto) {
		this.car_auto = car_auto;
	}
	public int getCar_use() {
		return car_use;
	}
	public void setCar_use(int car_use) {
		this.car_use = car_use;
	}
	public String getCar_accident() {
		return car_accident;
	}
	public void setCar_accident(String car_accident) {
		this.car_accident = car_accident;
	}
	public int getCar_owner_change() {
		return car_owner_change;
	}
	public void setCar_owner_change(int car_owner_change) {
		this.car_owner_change = car_owner_change;
	}
	public String getCar_design_op() {
		return car_design_op;
	}
	public void setCar_design_op(String car_design_op) {
		this.car_design_op = car_design_op;
	}
	public String getCar_con_op() {
		return car_con_op;
	}
	public void setCar_con_op(String car_con_op) {
		this.car_con_op = car_con_op;
	}
	public String getCar_drive_op() {
		return car_drive_op;
	}
	public void setCar_drive_op(String car_drive_op) {
		this.car_drive_op = car_drive_op;
	}
	public int getChecker_num() {
		return checker_num;
	}
	public void setChecker_num(int checker_num) {
		this.checker_num = checker_num;
	}
	public int getCar_status() {
		return car_status;
	}
	public void setCar_status(int car_status) {
		this.car_status = car_status;
	}
	public String getCar_checker_opinion() {
		return car_checker_opinion;
	}
	public void setCar_checker_opinion(String car_checker_opinion) {
		this.car_checker_opinion = car_checker_opinion;
	}
}
