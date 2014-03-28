package com.service;

import java.util.List;

import com.dto.UserDTO;

/**
 * This is UserServiceInt.
 * 
 * @author Chandrabhan
 * @version 1.1
 * 
 */
public interface UserServiceInt {
	public Long add(UserDTO userDTO) throws Exception;

	public UserDTO update(UserDTO userDTO) throws Exception;

	public Boolean changePassword(Integer userId, String oldPassword,
			String newPassword) throws Exception;

	public UserDTO get(Integer userId) throws Exception;

	public UserDTO authonticate(String login, String password) throws Exception;

	public List search(UserDTO userDTO) throws Exception;

	public Boolean lockUser(UserDTO userDTO) throws Exception;

	public UserDTO updateAccess(UserDTO userDTO) throws Exception;

	public List getSecurityQuestion() throws Exception;
	
	public List<UserDTO> getList() throws Exception;
	
	public void delete(Integer id) throws Exception;
}
