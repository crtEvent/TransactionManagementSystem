package com.sshmanager.ssh.main.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sshmanager.ssh.main.domain.FileType;
import com.sshmanager.ssh.main.dto.FileDTO;

@Repository("fileDAO")
public class FileDAO {
	
	@Autowired
    private SqlSession session;
	private static String namespace = "file.";
	
	public FileDTO selectFile(String file_idx) throws Exception {
		return session.selectOne(namespace+"selectFile", file_idx);
	}
	
	public List<FileDTO> selectFileByFileType(String company_idx, FileType file_type) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("company_idx", company_idx);
		map.put("file_type", file_type.getFile_type());
		map.put("date_condition", false);
		
		return session.selectList(namespace+"selectFileByFileType", map);
	}
	
	public List<FileDTO> selectFileByFileType(String company_idx, FileType file_type
			, String start_date, String end_date) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("company_idx", company_idx);
		map.put("file_type", file_type.getFile_type());
		map.put("start_date", start_date);
		map.put("end_date", end_date);
		map.put("date_condition", true);
		
		return session.selectList(namespace+"selectFileByFileType", map);
	}
	
	public List<FileDTO> selectFileByFileType(String company_idx, FileType file_type
			, String year) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("company_idx", company_idx);
		map.put("file_type", file_type.getFile_type());
		map.put("start_date", year+"-01-01");
		map.put("end_date", year+"-12-31");
		map.put("date_condition", true);
		
		return session.selectList(namespace+"selectFileByFileType", map);
	}
	
}
