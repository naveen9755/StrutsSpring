package com.test;

import java.util.Date;


import com.dto.UserDTO;
import com.locator.ServiceLocator;
import com.service.UserServiceInt;

public class TestUserAdd {

	public static void main(String[] args) throws Exception {
		UserDTO userDTO = new UserDTO();
		UserServiceInt userService = ServiceLocator.getInstance()
				.getUserService();
		//userDTO.setId(16);
		userDTO.setFirstName("Chandrabhan");
		userDTO.setLastName("Singh Sisodiya");
		userDTO.setLogin("chandrabhan.ips11@gmail.com");
		userDTO.setPassword("aa");
		Date date = new Date("2013/09/12 12:08:45");

		userDTO.setDateOfBirth(date);
		userDTO.setLastAccessTime(new Date());
		userDTO.setLockSummery(new Date());
		userDTO.setRole("ADMIN");
		userService.add(userDTO);
		//System.out.println(userDTO.getFirstName());

	}
}
