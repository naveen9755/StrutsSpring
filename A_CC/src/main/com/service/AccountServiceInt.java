package com.service;

import java.util.List;

import com.dto.AccountDTO;

/**
 * This is AccountServiceInt
 * 
 * @author Chandrabhan
 * @version 1.1
 * 
 */
public interface AccountServiceInt {

	public AccountDTO openAccount(AccountDTO accountDTO) throws Exception;

	public double balance(Integer accountId) throws Exception;

	public AccountDTO get(Integer accountId) throws Exception;

	public double withDrawal(Integer accountId, Double amount) throws Exception;

	public double deposite(Integer accountId, Double amount) throws Exception;

	public double fundTransfer(Integer depositerId, Integer reciverId,
			Double amount) throws Exception;

	public Boolean lockAccount(AccountDTO accountDTO) throws Exception;

	public List<AccountDTO> search(AccountDTO accountDTO) throws Exception;

}