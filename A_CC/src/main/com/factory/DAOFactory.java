package com.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.dao.AccountDAOHibImpl;
import com.dao.AccountDAOInt;
import com.dao.AccountDAOJDBCImpl;
import com.dao.UserDAOHibImpl;
import com.dao.UserDAOInt;
import com.dao.UserDAOJDBCImpl;

/**
 * This is DAO Factory .
 * 
 * @author Chandrabhan
 * 
 */
public class DAOFactory {
	/**
	 * Logger to record log messages in to a file or a fix destination.
	 */
	private static Logger LOGGER = Logger.getLogger(DAOFactory.class);
	/**
	 * Resource boundle to get information from non java file.
	 */
	private static ResourceBundle bundle = ResourceBundle
			.getBundle("com.bundle.ResourceBundle");
	/**
	 * Class type private attribute.
	 */
	private static DAOFactory factory = null;
	/**
	 * Sting that is use to select database. value get at runtime from resouce
	 * bundle.
	 */
	private static final String database = bundle.getString("database");
	private static Map session = new HashMap();

	/**
	 * private constructor
	 */
	private DAOFactory() {
	}

	/**
	 * getInstance method to get instance of DAO Factory.
	 * 
	 * @return DAOFactory
	 */
	public static DAOFactory getInstance() {
		if (factory == null) {
			factory = new DAOFactory();
		}
		return factory;
	}

	/**
	 * method to get UserDAOInt instance
	 * 
	 * @return UserDAOInt instance
	 */
	public UserDAOInt getUser() {
		LOGGER.debug("Debug:Now in getUser() in DAO factory");
		UserDAOInt userDAO = (UserDAOInt) session.get("userDAO");
		if (userDAO == null) {
			if ("Hibernate".equals(database)) {
				userDAO = new UserDAOHibImpl();
			} else if ("JDBC".equals(database)) {
				userDAO = new UserDAOJDBCImpl();
			}
			session.put("userDAO", userDAO);

		}
		return userDAO;
	}

	/**
	 * method to get AccountDAOInt instance
	 * 
	 * @return AccountDAOInt instance
	 */
	public AccountDAOInt getAccount() {
		LOGGER.debug("Debug:Now in getAccount() in DAO Factory");
		AccountDAOInt accountDAO = (AccountDAOInt) session.get("accountDAO");
		if (accountDAO == null) {
			if ("Hibernate".equals(database)) {
				accountDAO = new AccountDAOHibImpl();
			}
			if ("JDBC".equals(database)) {
				accountDAO = new AccountDAOJDBCImpl();
			}
			session.put("accountDAO", accountDAO);
		}
		return accountDAO;
	}

}