package com.sshmanager.ssh.main.dto;

public class FileDTO {
	
	private String file_idx;
	private String transaction_idx;
	private String classification;
	private String file_name;
	private String file_path;
	
	public String getFile_idx() {
		return file_idx;
	}
	public void setFile_idx(String file_idx) {
		this.file_idx = file_idx;
	}
	public String getTransaction_idx() {
		return transaction_idx;
	}
	public void setTransaction_idx(String transaction_idx) {
		this.transaction_idx = transaction_idx;
	}
	public String getClassification() {
		return classification;
	}
	public void setClassification(String classification) {
		this.classification = classification;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public String getFile_path() {
		return file_path;
	}
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	
	@Override
	public String toString() {
		return "FileDTO [file_idx=" + file_idx + ", transaction_idx=" + transaction_idx + ", classification="
				+ classification + ", file_name=" + file_name + ", file_path=" + file_path + "]";
	}
	
}
