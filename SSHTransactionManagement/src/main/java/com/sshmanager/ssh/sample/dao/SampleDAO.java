package com.sshmanager.ssh.sample.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sshmanager.ssh.main.dto.FileDTO;

@Repository("sampleDAO")
public class SampleDAO {
	
	@Autowired
    private SqlSession session;
	private static String namespace = "sample.";
	
	public String getTime() {
		return (String) session.selectOne(namespace+"selectNow");
	}
	
	public String testEnum(FileDTO file) {
		return (String) session.selectOne(namespace+"testEnum", file);
	}

}
