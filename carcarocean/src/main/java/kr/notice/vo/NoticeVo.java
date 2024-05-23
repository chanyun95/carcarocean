package kr.notice.vo;

public class NoticeVo {
	private int notice_num;
	private String notice_title;
	private String notice_content;
	private String notice_photo;
	private int notice_impt;
	private int notice_hit;
	private String notice_reg;
	private String notice_modify;
	
	public int getNotice_num() {
		return notice_num;
	}
	public void setNotice_num(int notice_num) {
		this.notice_num = notice_num;
	}
	public String getNotice_title() {
		return notice_title;
	}
	public void setNotice_title(String notice_title) {
		this.notice_title = notice_title;
	}
	public String getNotice_content() {
		return notice_content;
	}
	public void setNotice_content(String notice_content) {
		this.notice_content = notice_content;
	}
	public String getNotice_photo() {
		return notice_photo;
	}
	public void setNotice_photo(String notice_photo) {
		this.notice_photo = notice_photo;
	}
	public int getNotice_impt() {
		return notice_impt;
	}
	public void setNotice_impt(int notice_impt) {
		this.notice_impt = notice_impt;
	}
	public int getNotice_hit() {
		return notice_hit;
	}
	public void setNotice_hit(int notice_hit) {
		this.notice_hit = notice_hit;
	}
	public String getNotice_reg() {
		return notice_reg;
	}
	public void setNotice_reg(String notice_date) {
		this.notice_reg = notice_date;
	}
	public String getNotice_modify() {
		return notice_modify;
	}
	public void setNotice_modify(String notice_modify) {
		this.notice_modify = notice_modify;
	}
}
