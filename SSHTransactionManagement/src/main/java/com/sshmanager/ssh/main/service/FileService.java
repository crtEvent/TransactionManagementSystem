package com.sshmanager.ssh.main.service;

import java.util.List;

import com.sshmanager.ssh.main.domain.FileType;
import com.sshmanager.ssh.main.dto.FileDTO;

public interface FileService {
	
	/* 파일 불러오기 */
	public FileDTO getFile(String file_idx) throws Exception;
	
	/* 해당 년도의 이미지 파일 리스트 불러오기 */
	public List<FileDTO> getFileByFileType(String company_idx, FileType file_type
			, String year) throws Exception;
}
