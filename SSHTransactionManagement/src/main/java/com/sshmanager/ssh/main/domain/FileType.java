package com.sshmanager.ssh.main.domain;

public enum FileType {
	
	QUOTE("quote"), ORDER("order"), IMAGE("image"), OTHER("other");
	
	private final String file_type;
	
	FileType(String file_type) {
		this.file_type = file_type;
	}

	public String getFile_type() {
		return file_type;
	}
	
}
