package com.sshmanager.ssh.main.dto;

public class MemoDTO {
	
	private String memo_idx;
	private String transaction_idx;
	private String content;
	
	public String getMemo_idx() {
		return memo_idx;
	}
	public void setMemo_idx(String memo_idx) {
		this.memo_idx = memo_idx;
	}
	public String getTransaction_idx() {
		return transaction_idx;
	}
	public void setTransaction_idx(String transaction_idx) {
		this.transaction_idx = transaction_idx;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
