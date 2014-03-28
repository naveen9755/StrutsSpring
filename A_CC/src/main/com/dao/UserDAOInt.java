package com.dao;

import java.util.List;

import com.dto.UserDTO;

/**
 * This is UserDAO Interface
 * 
 * @author Chandrabhan
 * @version 1.1
 */
public interface UserDAOInt {
	void add(UserDTO userDTO) throws Exception;

	UserDTO findByPk(Integer id) throws Exception;

	void delete(UserDTO userDTO) throws Exception;

	void update(UserDTO userDTO) throws Exception;

	List<UserDTO> search(UserDTO userDTO) throws Exception;
	
	List<UserDTO> getList() throws Exception;
	

	List getsecurityQuestion() throws Exception;

}
