package com.sshmanager.ssh.sample.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("sampleDAO")
public class SampleDAO {
	
	@Autowired
    private SqlSession session;
	private static String namespace = "sample.";
	
	public String getTime() {
		return (String) session.selectOne(namespace+"selectNow");
	}

}
