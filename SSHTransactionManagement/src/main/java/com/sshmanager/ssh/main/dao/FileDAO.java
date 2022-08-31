package com.sshmanager.ssh.main.dao;

import java.util.HashMap;
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
	
}
