package kr.s_re.vo;


public class S_ReVo {
	private int s_re_num;
	private int sell_num;
	
	private int mem_num;
	private String s_re_title;
	private String s_re_content;
	private String s_re_reg;
	private String s_re_modify;
	
	private String sell_maker;
	private String sell_cname;
	
	private String mem_id;			//회원아이디
	private String mem_photo;		//회원 프로필 사진
	
	public String getMem_photo() {
		return mem_photo;
	}
	public void setMem_photo(String mem_photo) {
		this.mem_photo = mem_photo;
	}
	public int getSell_num() {
		return sell_num;
	}
	public void setSell_num(int sell_num) {
		this.sell_num = sell_num;
	}
	public String getSell_maker() {
		return sell_maker;
	}
	public void setSell_maker(String sell_maker) {
		this.sell_maker = sell_maker;
	}
	public String getSell_cname() {
		return sell_cname;
	}
	public void setSell_cname(String sell_cname) {
		this.sell_cname = sell_cname;
	}
	
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
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
	public String getS_re_title() {
		return s_re_title;
	}
	public void setS_re_title(String s_re_title) {
		this.s_re_title = s_re_title;
	}
	public String getS_re_content() {
		return s_re_content;
	}
	public void setS_re_content(String s_re_content) {
		this.s_re_content = s_re_content;
	}
	public String getS_re_reg() {
		return s_re_reg;
	}
	public void setS_re_reg(String s_re_reg) {
		this.s_re_reg = s_re_reg;
	}
	public String getS_re_modify() {
		return s_re_modify;
	}
	public void setS_re_modify(String s_re_modify) {
		this.s_re_modify = s_re_modify;
	}
	
}
