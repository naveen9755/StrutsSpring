package com.delegate;

import com.dto.AccountDTO;
import com.locator.ServiceLocator;
import com.service.AccountServiceInt;

public class AccountBussinessDelegate {
	private AccountBussinessDelegate() {
	}

	private static AccountServiceInt accountService = ServiceLocator
			.getInstance().getAccountService();

	public static AccountDTO openAccount(AccountDTO accountDTO)
			throws Exception {
		return accountService.openAccount(accountDTO);
	}

	public static double balance(Integer accountId) throws Exception {
		return accountService.balance(accountId);
	}

	public static AccountDTO get(Integer accountId) throws Exception {
		return accountService.get(accountId);
	}

	public static double withDrawal(Integer accountId, Double amount)
			throws Exception {
		return accountService.withDrawal(accountId, amount);
	}

	public static double deposite(Integer accountId, Double amount)
			throws Exception {
		return accountService.deposite(accountId, amount);
	}

	public static double fundTransfer(Integer depositerId, Integer reciverId,
			Double amount) throws Exception {
		return accountService.fundTransfer(depositerId, reciverId, amount);
	}

	public Boolean lockAccount(AccountDTO accountDTO) throws Exception {
		return accountService.lockAccount(accountDTO);
	}

}
