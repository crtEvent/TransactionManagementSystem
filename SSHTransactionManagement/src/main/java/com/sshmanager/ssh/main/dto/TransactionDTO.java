package com.sshmanager.ssh.main.dto;

public class TransactionDTO {
	
	private String transaction_idx;
	private String company_idx;
	private String transaction_type;
	private String date;
	private String subject;
	
	public String getTransaction_idx() {
		return transaction_idx;
	}
	public void setTransaction_idx(String transaction_idx) {
		this.transaction_idx = transaction_idx;
	}
	public String getCompany_idx() {
		return company_idx;
	}
	public void setCompany_idx(String company_idx) {
		this.company_idx = company_idx;
	}
	public String getTransaction_type() {
		return transaction_type;
	}
	public void setTransaction_type(String transaction_type) {
		this.transaction_type = transaction_type;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	@Override
	public String toString() {
		return "TransactionDTO [transaction_idx=" + transaction_idx + ", company_idx=" + company_idx + ", date=" + date
				+ ", subject=" + subject + "]";
	}
	
}
