package com.sshmanager.ssh.main.service;

import java.time.LocalDate;
import java.util.List;

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
	
	public List<FileDTO> getFileByFileType(String company_idx, FileType file_type
			, String year) throws Exception {
		
		if(year == null || year.equals("")) {
			LocalDate now = LocalDate.now();
			year = Integer.toString(now.getYear());
		}
		
		return fileDAO.selectFileByFileType(company_idx, file_type, year);
	}
	
}
