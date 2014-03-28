package com.test;

import java.util.Date;
import java.util.List;


import com.dto.AccountDTO;
import com.locator.ServiceLocator;
import com.service.AccountServiceInt;

public class TestAccountUpdate {
	public static void main(String[] args) throws Exception {
		AccountServiceInt accountService = ServiceLocator.getInstance()
				.getAccountService();
		AccountDTO accountDTO = new AccountDTO();
		//accountDTO=accountService.f1006);
	}
}