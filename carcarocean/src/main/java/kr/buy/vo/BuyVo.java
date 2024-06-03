package kr.buy.vo;

public class BuyVo {
	private int buy_num;
	private int mem_num;
	private int car_num;
	private String buy_reg;
	private int buy_status;
	
	
	public int getBuy_status() {
		return buy_status;
	}
	public void setBuy_status(int buy_status) {
		this.buy_status = buy_status;
	}
	public int getBuy_num() {
		return buy_num;
	}
	public void setBuy_num(int buy_num) {
		this.buy_num = buy_num;
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
	public String getBuy_reg() {
		return buy_reg;
	}
	public void setBuy_reg(String buy_reg) {
		this.buy_reg = buy_reg;
	}
}
