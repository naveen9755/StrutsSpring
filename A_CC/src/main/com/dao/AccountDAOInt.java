package com.dao;

import java.util.List;

import com.dto.AccountDTO;

/**
 * This is AccountDAO interface
 * @author Chandrabhan
 * @version 1.1
 *
 */
public interface AccountDAOInt {

	void add(AccountDTO accountDTO) throws Exception;

	void update(AccountDTO accountDTO) throws Exception;

	void delete(AccountDTO accountDTO) throws Exception;

	AccountDTO findByPK(Integer id) throws Exception;

	List<AccountDTO> search(AccountDTO accountDTO) throws Exception;

}