package com.sshmanager.ssh.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sshmanager.ssh.user.dao.UserDAO;
import com.sshmanager.ssh.user.dto.UserDTO;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDAO userDAO;
	
	public boolean checkUserExist(String user_id) throws Exception {
		return userDAO.countUser(user_id) > 0 ? true : false;
	}
	
	public UserDTO getUser(String user_id, String user_password) throws Exception {
		
		UserDTO user = userDAO.selectUser(user_id, user_password);
		return user;
	}
}
