package com.test;


import com.dto.UserDTO;
import com.locator.ServiceLocator;
import com.service.UserServiceInt;

public class TestUserGet {

	public static void main(String[] args) throws Exception {
		UserServiceInt userService = ServiceLocator.getInstance()
				.getUserService();
		UserDTO userDTO = new UserDTO();

		userDTO = userService.get(1);

		System.out.println(userDTO.getFirstName() + "\t"
				+ userDTO.getLastName() + "\t" + userDTO.getLogin() + "\t"
				+ userDTO.getPassword());

	}
}
