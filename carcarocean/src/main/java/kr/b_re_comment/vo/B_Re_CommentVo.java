package kr.b_re_comment.vo;

public class B_Re_CommentVo {
	private int b_re_comm_num;
	private int b_re_num;
	private int mem_num;
	private String b_re_comm_content;
	private int b_re_comm_report;
	private String b_re_comm_reg;
	
	//작성자ID, 프로필 사진
	private String mem_id;
	private String mem_photo;
	
	public String getMem_photo() {
		return mem_photo;
	}
	public void setMem_photo(String mem_photo) {
		this.mem_photo = mem_photo;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public int getB_re_comm_num() {
		return b_re_comm_num;
	}
	public void setB_re_comm_num(int b_re_comm_num) {
		this.b_re_comm_num = b_re_comm_num;
	}
	public int getB_re_num() {
		return b_re_num;
	}
	public void setB_re_num(int b_re_num) {
		this.b_re_num = b_re_num;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public String getB_re_comm_content() {
		return b_re_comm_content;
	}
	public void setB_re_comm_content(String b_re_comm_content) {
		this.b_re_comm_content = b_re_comm_content;
	}
	public int getB_re_comm_report() {
		return b_re_comm_report;
	}
	public void setB_re_comm_report(int b_re_comm_report) {
		this.b_re_comm_report = b_re_comm_report;
	}
	public String getB_re_comm_reg() {
		return b_re_comm_reg;
	}
	public void setB_re_comm_reg(String b_re_comm_reg) {
		this.b_re_comm_reg = b_re_comm_reg;
	}
	
}
