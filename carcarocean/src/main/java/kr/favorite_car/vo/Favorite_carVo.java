package kr.favorite_car.vo;

public class Favorite_carVo {
	private int fav_num;
	private int mem_num;
	private int car_num;
	
	
	public int getFav_num() {
		return fav_num;
	}
	public void setFav_num(int fav_num) {
		this.fav_num = fav_num;
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
	
	
	public Favorite_carVo(int fav_num, int mem_num, int car_num) {
		super();
		this.fav_num = fav_num;
		this.mem_num = mem_num;
		this.car_num = car_num;
	}
	public Favorite_carVo() {}

	
	
}
