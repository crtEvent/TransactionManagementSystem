package com.sshmanager.ssh.user.service;

import java.util.List;

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
	
	public List<UserDTO> getUserList() throws Exception {
		return userDAO.selectUserList();
	}
	
	public void insertUser(UserDTO dto) throws Exception {
		userDAO.insertUser(dto);
	}

	public void updateUser(UserDTO dto) throws Exception {
		userDAO.updateUser(dto);
	}

	public void deleteUser(String user_idx) throws Exception {
		userDAO.deleteUser(user_idx);
	}
}
