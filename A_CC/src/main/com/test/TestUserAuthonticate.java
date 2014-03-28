package com.test;


import com.delegate.UserBussinessDelegate;
import com.dto.UserDTO;
import com.locator.ServiceLocator;
import com.service.UserServiceInt;

public class TestUserAuthonticate {

	public static void main(String[] args) throws Exception {
		UserDTO userDTO = new UserDTO();

		userDTO = UserBussinessDelegate.authonticate("chandrabhan", "111");

		System.out.println(userDTO.getFirstName() + "\t"
				+ userDTO.getLastName() + "\t" + userDTO.getLogin() + "\t"
				+ userDTO.getPassword());

	}
}
