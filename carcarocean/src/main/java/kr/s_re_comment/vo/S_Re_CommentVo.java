package kr.s_re_comment.vo;

public class S_Re_CommentVo {
	private int s_re_comm_num;
	private int s_re_num;
	private int mem_num;
	private String s_re_comm_content;
	private int s_re_comm_report;
	private String s_re_comm_reg;
	
	private String mem_id;			//회원아이디
	private String mem_photo;		//회원 프로필 사진
	
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
	public int getS_re_comm_num() {
		return s_re_comm_num;
	}
	public void setS_re_comm_num(int s_re_comm_num) {
		this.s_re_comm_num = s_re_comm_num;
	}
	public int getS_re_num() {
		return s_re_num;
	}
	public void setS_re_num(int s_re_num) {
		this.s_re_num = s_re_num;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public String getS_re_comm_content() {
		return s_re_comm_content;
	}
	public void setS_re_comm_content(String s_re_comm_content) {
		this.s_re_comm_content = s_re_comm_content;
	}
	public int getS_re_comm_report() {
		return s_re_comm_report;
	}
	public void setS_re_comm_report(int s_re_comm_report) {
		this.s_re_comm_report = s_re_comm_report;
	}
	public String getS_re_comm_reg() {
		return s_re_comm_reg;
	}
	public void setS_re_comm_reg(String s_re_comm_reg) {
		this.s_re_comm_reg = s_re_comm_reg;
	}
	
}
