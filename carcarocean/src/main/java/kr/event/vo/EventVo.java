package kr.event.vo;

public class EventVo {
	private int event_num;				//이벤트 식별자
	private String event_title;			//제목
	private String event_content;		//내용
	private String event_photo;			//사진
	private int event_hit;
	private String event_reg;			//등록일
	private String event_modify;		//수정일
	
	public int getEvent_num() {
		return event_num;
	}
	public void setEvent_num(int event_num) {
		this.event_num = event_num;
	}
	public String getEvent_title() {
		return event_title;
	}
	public void setEvent_title(String event_title) {
		this.event_title = event_title;
	}
	public String getEvent_content() {
		return event_content;
	}
	public void setEvent_content(String event_content) {
		this.event_content = event_content;
	}
	public String getEvent_photo() {
		return event_photo;
	}
	public void setEvent_photo(String event_photo) {
		this.event_photo = event_photo;
	}
	public int getEvent_hit() {
		return event_hit;
	}
	public void setEvent_hit(int event_hit) {
		this.event_hit = event_hit;
	}
	public String getEvent_reg() {
		return event_reg;
	}
	public void setEvent_reg(String event_reg) {
		this.event_reg = event_reg;
	}
	public String getEvent_modify() {
		return event_modify;
	}
	public void setEvent_modify(String event_modify) {
		this.event_modify = event_modify;
	}
	
	
}
