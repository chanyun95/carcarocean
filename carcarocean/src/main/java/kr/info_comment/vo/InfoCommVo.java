package kr.info_comment.vo;

public class InfoCommVo {
	private int info_board_comm_num;
	private int info_board_num;
	private int mem_num;
	private String info_board_comm_content;
	private int info_board_comm_report;
	private String info_board_comm_reg;
	
	private String mem_id; //회원 아이디
	
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public int getInfo_board_comm_num() {
		return info_board_comm_num;
	}
	public void setInfo_board_comm_num(int info_board_comm_num) {
		this.info_board_comm_num = info_board_comm_num;
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
	public String getInfo_board_comm_content() {
		return info_board_comm_content;
	}
	public void setInfo_board_comm_content(String info_board_comm_content) {
		this.info_board_comm_content = info_board_comm_content;
	}
	public int getInfo_board_comm_report() {
		return info_board_comm_report;
	}
	public void setInfo_board_comm_report(int info_board_comm_report) {
		this.info_board_comm_report = info_board_comm_report;
	}
	public String getInfo_board_comm_reg() {
		return info_board_comm_reg;
	}
	public void setInfo_board_comm_reg(String info_board_comm_reg) {
		this.info_board_comm_reg = info_board_comm_reg;
	}
}
