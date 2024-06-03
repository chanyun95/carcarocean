package kr.board.vo;

public class ReportBoardVO {
	private int report_board_num;
	private int board_num;
	private int mem_num;
	
	public ReportBoardVO() {}
	
	public ReportBoardVO(int report_board_num, int board_num, int mem_num) {
		this.report_board_num = report_board_num;
		this.board_num = board_num;
		this.mem_num = mem_num;
	}

	public int getReport_board_num() {
		return report_board_num;
	}

	public void setReport_board_num(int report_board_num) {
		this.report_board_num = report_board_num;
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
}
