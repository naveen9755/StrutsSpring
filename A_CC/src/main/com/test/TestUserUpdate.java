package com.test;

import java.util.List;


import com.dto.UserDTO;
import com.locator.ServiceLocator;
import com.service.UserServiceInt;

public class TestUserUpdate {

	public static void main(String[] args) throws Exception {
		UserDTO userDTO = new UserDTO();
		UserServiceInt userService = ServiceLocator.getInstance()
				.getUserService();

		userDTO.setLastName("Jay");
		List list = userService.search(userDTO);
		System.out.println(list.size());
		if (list.size() == 1) {
			userDTO = (UserDTO) list.get(0);
			userDTO.setFirstName("Jay");
			userDTO.setLastName("Jain");
			userService.update(userDTO);
		}
	}
}
