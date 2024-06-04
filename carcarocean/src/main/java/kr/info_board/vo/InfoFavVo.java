package kr.info_board.vo;

public class InfoFavVo {
	private int info_board_num;
	private int mem_num;
	
	public InfoFavVo() {}
	
	public InfoFavVo(int info_board_num, int mem_num) {
		this.info_board_num = info_board_num;
		this.mem_num = mem_num;
	}
	
	
	
	public int getInfo_board_num() {
		return info_board_num;
	}
	public void setInfo_board_num(int info_board_num) {
		this.info_board_num = info_board_num;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
}
