package com.sshmanager.ssh.user.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sshmanager.ssh.user.dto.UserDTO;

@Repository("userDAO")
public class UserDAO {
	
	@Autowired
    private SqlSession session;
	private static String namespace = "user.";
	
	public int countUser(String user_id) throws Exception {
		return session.selectOne(namespace+"countUser", user_id);
	}
	
	public UserDTO selectUser(String user_id, String user_password) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", user_id);
		map.put("user_password", user_password);
		return session.selectOne(namespace+"selectUser", map);
	}
}
