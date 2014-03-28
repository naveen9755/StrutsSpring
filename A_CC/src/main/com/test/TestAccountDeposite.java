package com.test;


import com.locator.ServiceLocator;
import com.service.AccountServiceInt;

public class TestAccountDeposite {
	public static void main(String[] args) throws Exception {
		AccountServiceInt accountService = ServiceLocator.getInstance()
				.getAccountService();

		double str = accountService.deposite(1002,10000d);
		System.out.println(str);
	}
}