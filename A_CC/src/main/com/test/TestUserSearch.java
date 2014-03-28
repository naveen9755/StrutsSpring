package com.test;

import java.util.Date;
import java.util.Iterator;
import java.util.List;


import com.dto.UserDTO;
import com.locator.ServiceLocator;
import com.service.UserServiceInt;

public class TestUserSearch {

	public static void main(String[] args) throws Exception {
		UserServiceInt userService = ServiceLocator.getInstance()
				.getUserService();
		UserDTO userDTO = new UserDTO();

		userDTO.setFirstName("cha");
		userDTO.setLastName("singh");
		userDTO.setDateOfBirth(new Date("07/19/2013"));

		List list = userService.search(userDTO);
	

		Iterator iterator = list.iterator();
		while (iterator.hasNext()) {
			userDTO = (UserDTO) iterator.next();
			System.out.println(userDTO.getId() + "---"
					+ userDTO.getFirstName() + "---" + userDTO.getLastName()
					+ "---" + userDTO.getDateOfBirth());
		}

		userDTO.setLogin("jay");
		userDTO = userService.get(1001);

		System.out.println(userDTO.getFirstName());

	}
}
