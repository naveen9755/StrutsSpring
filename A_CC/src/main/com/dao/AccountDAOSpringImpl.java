package com.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.dto.AccountDTO;
import com.exception.DatabaseException;
import com.exception.DuplicateRecordException;

/**
 * This Class Demonstrats AccountDAOSpringImpl DAO Logics in Spring.
 * 
 * @author Chandrabhan
 * @version 1.1
 */
public class AccountDAOSpringImpl extends HibernateDaoSupport implements
		AccountDAOInt {
	/**
	 * Logger to write log messages in to log file.
	 */
	private static final Logger LOGGER = Logger
			.getLogger(AccountDAOJDBCImpl.class);
	private static AccountDTO accountDTO = null;
	/**
	 * DetachedCriteria instance criteria.
	 */
	private static DetachedCriteria criteria = null;
	private static final String DB_EXCEPTION = "Error:Error with DataBase Connection.";

	/**
	 * This Method Demonstrate Insert Record(Account) in DataBase.
	 * 
	 * @param accountDTO
	 *            instance of AccountDTO
	 * @return void
	 * @exception Duplicate
	 *                Record Exception,JDBC Exception.
	 * 
	 */
	public void add(final AccountDTO accountDTO) throws Exception {
		LOGGER.debug("Debug:Now In Add() AccountDAOSpringImpl----");
		AccountDTO dbDTO = null;
		dbDTO = findByPK(accountDTO.getId());

		if (dbDTO == null) {
			try {
				getHibernateTemplate().save(accountDTO);
			} catch (Exception e) {
				LOGGER.error("Error: In AccountDAOSpringImpl Add", e);
				throw new DatabaseException(DB_EXCEPTION + e);
			} finally {
				LOGGER.debug("Debug:Exit Add() AccountDAOSpringImpl----");
			}
		} else {
			LOGGER.error("Error: This is a Duplicate Record..for id--"
					+ accountDTO.getId());
			throw new DuplicateRecordException(
					"This is a Duplicate Record..for id--" + accountDTO.getId());
		}
	}

	/**
	 * This Method Demonstrate Delete Record(Account) from DataBase.
	 * 
	 * @param accountDTO
	 *            instance of AccountDTO
	 * @return void
	 * @exception DataBase
	 *                exception,JDBC Exception
	 * 
	 */
	public void delete(final AccountDTO accountDTO) throws Exception {
		LOGGER.debug("Debug:Now In delete() AccountDAOSpringImpl---");
		try {
			getHibernateTemplate().delete(accountDTO);

		} catch (Exception e) {
			logger.error("Error:In  delete() AccountDAOSpringImpl", e);
			TransactionAspectSupport.currentTransactionStatus()
					.setRollbackOnly();
			throw new DatabaseException(DB_EXCEPTION + e);
		} finally {
			LOGGER.debug("Debug:Exit Deleted()...AccountDAOSpringImpl");
		}

	}

	/**
	 * This Method Demonstrate Update Record(Account) from DataBase.
	 * 
	 * @param accountDTO
	 *            instance of AccountDTO
	 * @return void
	 * @exception DataBase
	 *                exception,JDBC Exception
	 * 
	 */
	public void update(final AccountDTO accountDTO) throws Exception {
		LOGGER.debug("Debug:Now In update() AccountDAOSpringImpl---");
		try {
			getHibernateTemplate().update(accountDTO);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus()
					.setRollbackOnly();
			LOGGER.error("Error:In update", e);
			throw new DatabaseException(DB_EXCEPTION + e);
		} finally {
			LOGGER.debug("Debug:Exit  update() AccountDAOSpringImpl---");
		}
	}

	/**
	 * This Method Demonstrate Get Record(Account) from DataBase.
	 * 
	 * @param accountDTO
	 *            instance of AccountDTO
	 * @return accountDTO
	 * @exception DataBase
	 *                exception,JDBC Exception
	 * 
	 */

	public AccountDTO findByPK(final Integer id) throws Exception {
		LOGGER.debug("Debug:Now In findByPk() AccountDAOHibImpl...");
		try {
			accountDTO = new AccountDTO();
			accountDTO = (AccountDTO) getHibernateTemplate().get(
					AccountDTO.class, id);
		} catch (Exception e) {
			LOGGER.error("Error: In FindByPk..", e);
			throw new DatabaseException(DB_EXCEPTION + e);
		} finally {
			LOGGER.debug("Debug:Exit In findByPk() AccountDAOHibImpl...");
		}
		return accountDTO;
	}

	/**
	 * This Method Demonstrate Search Record(Account) from DataBase.
	 * 
	 * @param accountDTO
	 *            instance of AccountDTO
	 * @return list
	 * @exception DataBase
	 *                exception,JDBC Exception
	 * 
	 */
	public List<AccountDTO> search(final AccountDTO accountDTO)
			throws Exception {
		List<AccountDTO> list = new ArrayList<AccountDTO>();
		try {
			LOGGER.debug("Debug:Now In search() AccountDAOHibImpl---");

			criteria = DetachedCriteria.forClass(AccountDTO.class);

			if (accountDTO.getId() != null) {
				criteria.add(Restrictions.eq("accountId", accountDTO.getId()));
			}
			if (accountDTO.getOpenDate() != null) {
				criteria.add(Restrictions.eq("openDate", accountDTO
						.getBalance()));
			}
			if (accountDTO.getBalance() != null) {
				criteria.add(Restrictions
						.eq("balance", accountDTO.getBalance()));
			}
			if (accountDTO.getType() != null) {
				criteria.add(Restrictions.eq("type", accountDTO.getBalance()));
			}

			list = getHibernateTemplate().findByCriteria(criteria);
		} catch (Exception e) {
			LOGGER.error("Error: In Search", e);
			throw new DatabaseException(DB_EXCEPTION + e);
		} finally {
			LOGGER.debug("Debug:Now In Search() AccountDAOHibImpl...");
		}
		return list;
	}

}