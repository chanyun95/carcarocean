package kr.chat.vo;

public class ChatVo {
	private int chat_num;
	private int item_num; 			//상품
	private int chat_receiver;		//받는사람
	private int chat_giver;		//보낸사람
	private String chat_message;
	private String chat_reg;
	private int chat_check;
	public int getChat_num() {
		return chat_num;
	}
	public void setChat_num(int chat_num) {
		this.chat_num = chat_num;
	}
	
	public int getItem_num() {
		return item_num;
	}
	public void setItem_num(int item_num) {
		this.item_num = item_num;
	}
	public int getChat_receiver() {
		return chat_receiver;
	}
	public void setChat_receiver(int chat_receiver) {
		this.chat_receiver = chat_receiver;
	}
	public int getChat_giver() {
		return chat_giver;
	}
	public void setChat_giver(int chat_giver) {
		this.chat_giver = chat_giver;
	}
	public String getChat_message() {
		return chat_message;
	}
	public void setChat_message(String chat_message) {
		this.chat_message = chat_message;
	}
	public String getChat_reg() {
		return chat_reg;
	}
	public void setChat_reg(String chat_reg) {
		this.chat_reg = chat_reg;
	}
	public int getChat_check() {
		return chat_check;
	}
	public void setChat_check(int chat_check) {
		this.chat_check = chat_check;
	}
}
