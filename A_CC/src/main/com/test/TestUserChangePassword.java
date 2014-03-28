package com.test;

import com.locator.ServiceLocator;
import com.service.UserServiceInt;

public class TestUserChangePassword {

	public static void main(String[] args) throws Exception {
		UserServiceInt userService = ServiceLocator.getInstance()
				.getUserService();

		Boolean string = userService.changePassword(2, "aa", "321");
		System.out.println(string);

	}
}
