package kr.board_comment.vo;

public class Board_CommentVo {
	private int bor_comm_num;
	private int board_num;
	private int mem_num;
	private String bor_comm_content;
	private int bor_comm_report;
	private String bor_comm_reg;
	
	public int getBor_comm_num() {
		return bor_comm_num;
	}
	public void setBor_comm_num(int bor_comm_num) {
		this.bor_comm_num = bor_comm_num;
	}
	public int getBoard_num() {
		return board_num;
	}
	public void setBoard_num(int board_num) {
		this.board_num = board_num;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public String getBor_comm_content() {
		return bor_comm_content;
	}
	public void setBor_comm_content(String bor_comm_content) {
		this.bor_comm_content = bor_comm_content;
	}
	public int getBor_comm_report() {
		return bor_comm_report;
	}
	public void setBor_comm_report(int bor_comm_report) {
		this.bor_comm_report = bor_comm_report;
	}
	public String getBor_comm_reg() {
		return bor_comm_reg;
	}
	public void setBor_comm_reg(String bor_comm_reg) {
		this.bor_comm_reg = bor_comm_reg;
	}
	
}
