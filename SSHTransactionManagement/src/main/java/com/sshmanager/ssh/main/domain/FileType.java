package com.sshmanager.ssh.main.domain;

public enum FileType {
	
	QUOTE("quote","견적서"), ORDER("order","작업지시서"), IMAGE("image","사진"), OTHER("other","기타");
	
	private final String file_type;
	private final String folder_name;
	
	FileType(String file_type, String folder_name) {
		this.file_type = file_type;
		this.folder_name = folder_name;
	}

	public String getFile_type() {
		return file_type;
	}

	public String getFolder_name() {
		return folder_name;
	}
	
}
