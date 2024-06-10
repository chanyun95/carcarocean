package kr.chat.vo;

import kr.item.vo.ItemVo;
import kr.member.vo.MemberVo;

public class ChatVo {
	private int chat_num;
	private ItemVo item; 			//상품
	private MemberVo receiver;		//받는사람
	private MemberVo giver;		//보낸사람
	private String chat_message;
	private String chat_reg;
	private int chat_check;
	public int getChat_num() {
		return chat_num;
	}
	public void setChat_num(int chat_num) {
		this.chat_num = chat_num;
	}
	public ItemVo getItem() {
		return item;
	}
	public void setItem(ItemVo item) {
		this.item = item;
	}
	public MemberVo getReceiver() {
		return receiver;
	}
	public void setReceiver(MemberVo receiver) {
		this.receiver = receiver;
	}
	public MemberVo getGiver() {
		return giver;
	}
	public void setGiver(MemberVo giver) {
		this.giver = giver;
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
