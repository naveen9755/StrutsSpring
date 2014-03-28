package com.test;


import com.dto.AccountDTO;
import com.locator.ServiceLocator;
import com.service.AccountServiceInt;

public class TestAccountGet {
	public static void main(String[] args) throws Exception {
		AccountServiceInt accountService = ServiceLocator.getInstance()
				.getAccountService();
		AccountDTO accountDTO = new AccountDTO();

		accountDTO = accountService.get(1006);
		System.out.println("A/C Id" + "\tBalance\t" + "\tOpening---------Date"
				+ "\tType");
		System.out.print(accountDTO.getId());
		System.out.print("\t" + accountDTO.getBalance());
		System.out.print("\t" + accountDTO.getOpenDate());
		System.out.print("\t" + accountDTO.getType());

	}
}