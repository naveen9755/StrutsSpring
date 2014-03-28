package com.test;

import java.util.List;


import com.dto.UserDTO;
import com.locator.ServiceLocator;
import com.service.UserServiceInt;

public class TestUserDelete {

	public static void main(String[] args) throws Exception {
		UserDTO userDTO = new UserDTO();
		UserServiceInt userService = ServiceLocator.getInstance()
				.getUserService();

		userDTO.setLogin("vijay");
		List list = userService.search(userDTO);
		System.out.println(list.size());
		if (list.size() == 1) {
			userDTO=(UserDTO)list.get(0);
			//userService.delete(userDTO);
		}

	}
}
