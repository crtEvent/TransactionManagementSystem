package com.sshmanager.ssh.main.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("pathDAO")
public class PathDAO {
	
	@Autowired
    private SqlSession session;
	private static String namespace = "path.";
	
	public String selectPathByPathName(String path_name) throws Exception {
		return session.selectOne(namespace+"selectPathByPathName", path_name);
	}
	
	public String selectFileRootPath() throws Exception {
		return session.selectOne(namespace+"selectPathByPathName", "FILE_ROOT");
	}
	
}
