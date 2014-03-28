package com.test;


import com.locator.ServiceLocator;
import com.service.AccountServiceInt;

public class TestAccountBalance {
	public static void main(String[] args) throws Exception {
		AccountServiceInt accountService = ServiceLocator.getInstance()
				.getAccountService();
		double balance = accountService.balance(1006);
		
		System.out.println(balance);

	}
}