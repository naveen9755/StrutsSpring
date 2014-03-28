package com.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.dao.AccountDAOInt;
import com.dto.AccountDTO;
import com.emailService.MailServiceInt;
import com.exception.AccessDeniedException;
import com.exception.ApplicationException;
import com.exception.DuplicateRecordException;
import com.exception.InsufficientFundException;
import com.exception.UserNotFoundException;
import com.factory.DAOFactory;
import com.locator.ServiceLocator;

/**
 * This class Demonstrate AccountServiceImpl have Bussiness logics only.
 * 
 * @author Chandrabhan
 * @version 1.1
 * 
 */
public class AccountServiceSpringImpl implements AccountServiceInt {
	private ResourceBundle bundle = ResourceBundle
			.getBundle("com.bundle.email");
	/**
	 * Logger to record log's in file
	 */
	private static Logger LOGGER = Logger.getLogger(AccountServiceSpringImpl.class);
	/**
	 * create refrence or instance of DAO classes with help of DAO factory
	 */
	private AccountDAOInt accountDAO = DAOFactory.getInstance().getAccount();
	/**
	 * creating instance of AccountDTO
	 */
	private AccountDTO accountDTO = new AccountDTO();
	/**
	 * eMail class instance
	 */
	MailServiceInt mailService = ServiceLocator.getInstance().getMailService();

	/**
	 * This Method Demonstrate deposite.
	 * 
	 * @param depositerId-Integer
	 * @param amount-double
	 * @return balance of depositer account -double
	 * 
	 */
	public double deposite(Integer depositerId, Double amount) throws Exception {
		LOGGER.debug("Debug:Now in deposite() AccountServiceImpl... ");
		double balance = 0;
		try {
			accountDTO = accountDAO.findByPK(depositerId);

			if (accountDTO != null && depositerId.equals(accountDTO.getId())) {
				accountDTO.setBalance(accountDTO.getBalance() + amount);
				accountDAO.update(accountDTO);
				accountDTO = get(accountDTO.getId());
				balance = accountDTO.getBalance();
				mailService.sendMail(bundle.getString("email.to"), bundle
						.getString("emailSubject.Account"), bundle
						.getString("emailMessage.Account")
						+ balance);

			} else {
				LOGGER
						.error("Error:user not found exception in depostie AccountServiceImpl for accountId"
								+ depositerId);
				throw new UserNotFoundException(
						"Error:user not found exception in depostie AccountServiceImpl for accountId"
								+ depositerId);

			}
		} catch (Exception e) {
			LOGGER
					.error(
							"Error:Their is an Applicaction In Deposite is AccountServiceImpl",
							e);
			throw new ApplicationException(
					"Error: Their is an application exception in deposite AccountServiceImpl"
							+ e);
		}
		return balance;
	}

	/**
	 * This Method Demonstrat Fundtransfer
	 * 
	 * @param depositerId-Integer
	 * @param reciverId-Integer
	 * @param amount-double
	 * @return balance of depositer account -double
	 * @exception accessdenide,insuficientfund
	 *                exception
	 * @see deposite() ande withdrawal()
	 */

	public double fundTransfer(Integer depositerId, Integer reciverId,
			Double amount) throws Exception {
		LOGGER.debug("Debug:Now in fundTransfer() AccountServiceImpl... ");
		double balance = 0;

		accountDTO = accountDAO.findByPK(depositerId);
		if (accountDTO != null && depositerId.equals(accountDTO.getId())) {
			if (amount < accountDTO.getBalance()) {

				accountDTO = accountDAO.findByPK(reciverId);

				AccountDTO accountDTO2 = accountDAO.findByPK(reciverId);
				if (accountDTO2 != null
						&& reciverId.equals(accountDTO2.getId())) {
					balance = withDrawal(depositerId, amount);
					deposite(reciverId, amount);
				}
			} else {
				LOGGER
						.error("Error:InsufficientFundException in fundtransfer() for depositerId-"
								+ depositerId);
				throw new InsufficientFundException(
						"Error:InsufficientFundException in fundtransfer() for depositerId-"
								+ depositerId);
			}
		} else {
			LOGGER
					.error("Error:AccessDeniedException in fundTransfer() in AccountServiceImpl....");
			throw new AccessDeniedException(
					"Error:AccessDeniedException in fundTransfer() in AccountServiceImpl...."
							+ depositerId);
		}
		return balance;
	}

	/**
	 * This Method Demonstrat LockAccount
	 * 
	 * @param accountDTO
	 *            instance of AccountDTO.class
	 * @return boolean.
	 * 
	 */
	public Boolean lockAccount(AccountDTO accountDTO) throws Exception {
		LOGGER.debug("Debug:Now in lockAccount() AccountServiceImpl... ");
		accountDTO = accountDAO.findByPK(accountDTO.getId());
		Calendar time = Calendar.getInstance();
		time.setTime(new Date());
		time.add(Calendar.HOUR, 1);
		accountDAO.update(accountDTO);
		return true;
	}

	/**
	 * This Method Demonstrat OpenAccount
	 * 
	 * @param accountDTO
	 *            instance of AccountDTO.class
	 * @return accountDTO instance of AccountDTO
	 * 
	 */
	public AccountDTO openAccount(AccountDTO accountDTO) throws Exception {
		LOGGER.debug("Debug:Now in OpenAccount() AccountServiceImpl... ");
		AccountDTO dbDTO = new AccountDTO();
		try {
			dbDTO = accountDAO.findByPK(accountDTO.getId());
			if (dbDTO == null) {
				if (accountDTO.getBalance() >= 1000d) {
					accountDAO.add(accountDTO);
					dbDTO = accountDAO.findByPK(accountDTO.getId());
				} else {
					throw new InsufficientFundException(
							"Insuficient Fund for Account" + accountDTO.getId());
				}
			} else {
				throw new DuplicateRecordException(
						"This is Duplicate Id for Account "
								+ accountDTO.getId());
			}
		} catch (Exception e) {
			throw new ApplicationException(
					" Their is an application exception " + e);
		}
		return accountDTO;
	}

	/**
	 * This Method Demonstrat withDrawal
	 * 
	 * @param reciverId-Integer
	 * @return balance of reciver account -double
	 * 
	 */

	public double withDrawal(Integer reciverId, Double amount) throws Exception {
		LOGGER.debug("Debug:Now in withDrawal() AccountServiceImpl... ");

		double balance = 0;
		try {
			accountDTO = accountDAO.findByPK(reciverId);
			if (accountDTO != null && reciverId.equals(accountDTO.getId())) {

				if (amount < accountDTO.getBalance()) {
					accountDTO.setBalance(accountDTO.getBalance() - amount);

					accountDAO.update(accountDTO);
					accountDTO = accountDAO.findByPK(accountDTO.getId());
					balance = accountDTO.getBalance();
				} else {
					LOGGER
							.error("Error:InsuficientFund Exception in withDrawal for id--"
									+ reciverId);
					throw new InsufficientFundException(
							"Error:InsuficientFund Exception in withDrawal for id--"
									+ reciverId);
				}
			} else {
				LOGGER
						.error("Error:User not found --In valid AccountId in withrawal method AccountServiceImpl");
				throw new UserNotFoundException("Usernot fount Exception"
						+ reciverId);
			}
		} catch (Exception e) {
			LOGGER
					.error("Error:Application in withrawal method AccountServiceImpl"
							+ e);
			throw new ApplicationException("Their is an application exception"
					+ e);

		}
		return balance;
	}

	/**
	 * This Method Demonstrat Balance
	 * 
	 * @param accountId-Integer
	 * @return balance of account -double
	 * 
	 */
	public double balance(Integer accountId) throws Exception {
		LOGGER.debug("Debug:Now in balance() AccountServiceImpl... ");
		double balance = 0.00;
		try {
			accountDTO = accountDAO.findByPK(accountId);
			if (accountDTO.getId() == accountId) {
				balance = accountDTO.getBalance();
			} else {
				LOGGER.error("Error:User not found Exception for id"
						+ accountId);
				throw new UserNotFoundException(
						"Error:User not found Exception for id" + accountId);
			}
		} catch (Exception e) {
			LOGGER
					.error("Error: Their is an application exception in Balance AccountServiceImp"
							+ e);
			throw new ApplicationException("Their is an application exception"
					+ e);
		}
		return balance;
	}

	/**
	 * This Method Demonstrat Fundtransfer
	 * 
	 * @param accountId-Integer
	 * @return accountDTO instance of AccountDTO.class
	 * 
	 */
	public AccountDTO get(Integer accountId) throws Exception {
		LOGGER.debug("Debug:Now in get() AccountServiceImpl... ");
		try {
			accountDTO = accountDAO.findByPK(accountId);
			if (accountId != accountDTO.getId()) {
				LOGGER.error("Error: User not found Exception for accountid"
						+ accountId);
				throw new UserNotFoundException(
						"Error: User not found Exception for accountid"
								+ accountId);
			}
		} catch (Exception e) {
			LOGGER.error("Error: Application Exception for accountid" + e);
			throw new ApplicationException(
					"Error: Application Exception for accountid" + e);
		}
		return accountDTO;
	}

	/**
	 * This Method Demonstrat Seaarch
	 * 
	 * @param accountDTO
	 *            instance of AccountDTO.class
	 * @return list of all posible search record -list
	 * 
	 */
	public List<AccountDTO> search(AccountDTO accountDTO) throws Exception {
		LOGGER.debug("Debug:Now in search() AccountServiceImpl... ");
		List list = new ArrayList();
		try {
			list = accountDAO.search(accountDTO);
		} catch (Exception e) {
			LOGGER
					.error("Error: Their is an application exception in accountServiceImpl search"
							+ e);
			throw new ApplicationException(
					"Error: Their is an application exception in accountServiceImpl search"
							+ e);
		}
		return list;
	}

}