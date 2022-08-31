package com.sshmanager.ssh.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sshmanager.ssh.main.dao.FileDAO;
import com.sshmanager.ssh.main.domain.FileType;
import com.sshmanager.ssh.main.dto.FileDTO;

@Service("fileService")
public class FileServiceImpl implements FileService {
	
	@Autowired
	private FileDAO fileDAO;
	
	public FileDTO getFile(String file_idx) throws Exception {
		return fileDAO.selectFile(file_idx);
	}
	
}
