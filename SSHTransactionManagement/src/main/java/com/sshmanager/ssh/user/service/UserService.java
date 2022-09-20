package com.sshmanager.ssh.user.service;

import com.sshmanager.ssh.user.dto.UserDTO;

public interface UserService {
	
	/* 전달된 id를 가진 유저가 존재하는지 확인 */
	public boolean checkUserExist(String user_id) throws Exception;
	
	/* id, password가 일치하는 유저정보 불러오기 */
	public UserDTO getUser(String user_id, String user_password) throws Exception;

}
