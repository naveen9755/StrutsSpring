package com.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import com.dto.AccountDTO;
import com.exception.DatabaseException;
import com.exception.DuplicateRecordException;

/**
 * This is AccountDAOInt Hibernate Implementation class.
 * 
 * @author Chandrabhan Singh
 * @version 1.1
 */
public class AccountDAOHibImpl implements AccountDAOInt {
	/**
	 * Logger to write log messages in file.
	 */
	private static final Logger LOGGER = Logger
			.getLogger(AccountDAOJDBCImpl.class);
	/**
	 * Session factory
	 */

	private static SessionFactory sessionFactory = new Configuration()
			.configure().buildSessionFactory();
	/**
	 * Session
	 */
	private transient Session session = null;
	/**
	 * transaction
	 */
	private transient Transaction transaction = null;

	private static AccountDTO accountDTO = null;

	/**
	 * This Method Demonstrate Insert Record(Account) in DataBase.
	 * 
	 * @param accountDTO
	 * @return nothing
	 * @exception Duplicate
	 *                Record Exception,JDBC Exception.
	 *  @throws Exception
	 * 
	 */
	public void add(AccountDTO accountDTO) throws Exception {
		LOGGER.debug("Debug:Now In add()...AccountHibImpl");
		AccountDTO dbDTO = findByPK(accountDTO.getId());
		if (dbDTO == null) {
			try {
				session = sessionFactory.openSession();
				transaction = session.beginTransaction();
				session.save(accountDTO);
				transaction.commit();
			} catch (Exception e) {
				transaction.rollback();
				LOGGER.error("Error: In AccountDAOHibImpl add", e);
			} finally {
				session.close();
				LOGGER.debug("Debug:Exit-- Add(AccountDAOHibImpl)---");
			}
		} else {
			LOGGER.error("Debug:Duplicate Record");
			throw new DuplicateRecordException(
					"This is a Duplicate record for:" + dbDTO.getId());
		}

	}

	/**
	 * This Method Demonstrate Delete Record(Account) from DataBase.
	 * 
	 * @param accountDTO
	 * @return void
	 * @exception DataBase
	 *                exception,JDBC Exception
	 * 
	 */
	public void delete(AccountDTO accountDTO) throws Exception {
		try {
			LOGGER.debug("Now In delete()...");
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.delete(accountDTO);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			LOGGER.error("Error: In AccountDAOHibImpl delete", e);
			throw new DatabaseException("Error with DataBase Connection");

		} finally {
			session.close();
			LOGGER.debug("Debug:Exit-- Delete(AccountDAOHibImpl)---");
		}
	}

	/**
	 * This Method Demonstrate Update Record(Account) from DataBase.
	 * 
	 * @param accountDTO
	 * @return void
	 * @exception DataBase
	 *                exception,JDBC Exception
	 * 
	 */

	public void update(AccountDTO accountDTO) throws Exception {
		try {
			LOGGER.debug("Now In update()...");
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.update(accountDTO);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			LOGGER.error("Error: In AccountDAOHibImpl Update", e);
			throw new DatabaseException("Error with DataBase Connection");
		} finally {
			session.close();
			LOGGER.debug("Debug:Exit-- update(AccountDAOHibImpl)---");
		}
	}

	/**
	 * This Method Demonstrate Get Record(Account) by PrimaryKey from DataBase.
	 * 
	 * @param accountDTO
	 * @return accountDTO
	 * @exception DataBase
	 *                exception,JDBC Exception
	 * 
	 */
	public AccountDTO findByPK(Integer id) throws Exception {
		try {
			LOGGER.debug("Now In findByPk()...");
			accountDTO = new AccountDTO();
			session = sessionFactory.openSession();
			accountDTO = (AccountDTO) session.get(AccountDTO.class, id);
		} catch (Exception e) {
			LOGGER.error("Error: In AccountDAOHibImpl findByPk", e);
			throw new DatabaseException("Error with DataBase Connection");
		} finally {
			session.close();
			LOGGER.debug("Debug:Exit-- FindByPk(AccountDAOHibImpl)---");
		}
		return accountDTO;
	}

	/**
	 * This Method Demonstrate Search Record(Account) from DataBase. Search with
	 * the help of all possible combination or not.
	 * 
	 * @param accountDTO
	 * @return list
	 * @exception DataBase
	 *                exception,JDBC Exception
	 * 
	 */

	public List<AccountDTO> search(AccountDTO accountDTO) throws Exception {
		List<AccountDTO> list = new ArrayList<AccountDTO>();
		try {
			LOGGER.debug("Now In search()...");

			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(AccountDTO.class);

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
			list = criteria.list();
		} catch (Exception e) {
			LOGGER.error("Error: In AccountDAOHibImpl Update", e);
			throw new DatabaseException("Error with DataBase Connection");
		} finally {
			session.close();
			LOGGER.debug("Debug:Exit-- Add(AccountDAOHibImpl)---");
		}
		return list;
	}
}