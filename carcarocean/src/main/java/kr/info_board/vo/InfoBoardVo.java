package kr.info_board.vo;

public class InfoBoardVo {
	private int info_board_num;
	private int mem_num;
	private String info_board_title;
	private String info_board_content;
	private String info_board_photo;
	private int info_board_hit;
	private int info_board_report;
	private String info_board_reg;
	private String info_board_modify;
	
	private int likes; //좋아요 수 
	
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	private String mem_id;
	private String mem_photo;
	
	
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
	public String getInfo_board_title() {
		return info_board_title;
	}
	public void setInfo_board_title(String info_board_title) {
		this.info_board_title = info_board_title;
	}
	public String getInfo_board_content() {
		return info_board_content;
	}
	public void setInfo_board_content(String info_board_content) {
		this.info_board_content = info_board_content;
	}
	public String getInfo_board_photo() {
		return info_board_photo;
	}
	public void setInfo_board_photo(String info_board_photo) {
		this.info_board_photo = info_board_photo;
	}
	public int getInfo_board_hit() {
		return info_board_hit;
	}
	public void setInfo_board_hit(int info_board_hit) {
		this.info_board_hit = info_board_hit;
	}
	public int getInfo_board_report() {
		return info_board_report;
	}
	public void setInfo_board_report(int info_board_report) {
		this.info_board_report = info_board_report;
	}
	public String getInfo_board_reg() {
		return info_board_reg;
	}
	public void setInfo_board_reg(String info_board_reg) {
		this.info_board_reg = info_board_reg;
	}
	public String getInfo_board_modify() {
		return info_board_modify;
	}
	public void setInfo_board_modify(String info_board_modify) {
		this.info_board_modify = info_board_modify;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getMem_photo() {
		return mem_photo;
	}
	public void setMem_photo(String mem_photo) {
		this.mem_photo = mem_photo;
	}
}
