package com.sshmanager.ssh.main.dto;

import com.sshmanager.ssh.main.domain.FileType;

public class FileDTO {
	
	private String file_idx;
	private String transaction_idx;
	private String file_name;
	private String file_path;
	private FileType file_type;
	
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
	public FileType getFile_type() {
		return file_type;
	}
	public void setFile_type(FileType file_type) {
		this.file_type = file_type;
	}
	@Override
	public String toString() {
		return "FileDTO [file_idx=" + file_idx + ", transaction_idx=" + transaction_idx + ", file_name=" + file_name
				+ ", file_path=" + file_path + ", file_type=" + file_type + "]";
	}
}
