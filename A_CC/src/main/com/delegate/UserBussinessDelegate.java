package com.delegate;

import java.util.List;

import com.dto.UserDTO;
import com.locator.ServiceLocator;
import com.service.UserServiceInt;

public class UserBussinessDelegate {
	private UserBussinessDelegate() {
	}

	private static UserServiceInt userService = ServiceLocator.getInstance()
			.getUserService();

	public static Long add(UserDTO userDTO) throws Exception {
		return userService.add(userDTO);
	}

	public static UserDTO update(UserDTO userDTO) throws Exception {
		return userService.update(userDTO);
	}

	public static Boolean changePassword(Integer userId, String oldPassword,
			String newPassword) throws Exception {
		System.out.println("Id---------" + userId);
		System.out.println("pass--------" + oldPassword);
		System.out.println("pass--------" + newPassword);
		return userService.changePassword(userId, oldPassword, newPassword);
	}

	public static UserDTO get(Integer userId) throws Exception {
		return userService.get(userId);
	}

	public static UserDTO authonticate(String login, String password)
			throws Exception {
		return userService.authonticate(login, password);
	}

	public static List search(UserDTO userDTO) throws Exception {
		return userService.search(userDTO);
	}

	public static boolean lockUser(UserDTO userDTO) throws Exception {
		return userService.lockUser(userDTO);
	}

	public static List getSecurityQuestion() throws Exception {
		return userService.getSecurityQuestion();
	}
	
	public static List getList() throws Exception {
		return userService.getList();
	}
	
	public static void delete(Integer id) throws Exception {
		 userService.delete(id);
	}

}
