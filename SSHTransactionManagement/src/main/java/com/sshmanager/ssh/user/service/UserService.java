package com.sshmanager.ssh.user.service;

import java.util.List;

import com.sshmanager.ssh.user.dto.UserDTO;

public interface UserService {
	
	/* 전달된 id를 가진 유저가 존재하는지 확인 */
	public boolean checkUserExist(String user_id) throws Exception;
	
	/* id, password가 일치하는 유저정보 불러오기 */
	public UserDTO getUser(String user_id, String user_password) throws Exception;
	
	/* 유저 리스트 불러오기 */
	public List<UserDTO> getUserList() throws Exception;
	
	/* 계정 등록 */
	public void insertUser(UserDTO dto) throws Exception;
	
	/* 계정 수정 */
	public void updateUser(UserDTO dto) throws Exception;
	
	/* 계정 삭제 */
	public void deleteUser(String user_idx) throws Exception;
}
