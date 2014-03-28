package com.test;


import com.locator.ServiceLocator;
import com.service.AccountServiceInt;

public class TestAccountWithdrawal {
	public static void main(String[] args) throws Exception {
		AccountServiceInt accountService = ServiceLocator.getInstance()
				.getAccountService();

		double str = accountService.withDrawal(1001, 9000d);
		System.out.println(str);
	}
}