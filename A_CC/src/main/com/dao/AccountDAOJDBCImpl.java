package com.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.dto.AccountDTO;
import com.exception.DatabaseException;
import com.exception.DuplicateRecordException;
import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * This class Demonstrate AccountDAO JDBC Implementation
 * 
 * @author Chandrabhan Singh
 * @version 1.1 05/08/2013
 * 
 */
public class AccountDAOJDBCImpl implements AccountDAOInt {

	/**
	 * Resource bundle to get values at runtime from non java file.
	 */
	private static final ResourceBundle BUNDLE = ResourceBundle
			.getBundle("com.bundle.ResourceBundle");
	/**
	 * Logger to write logs in log or text file.
	 */
	private static final Logger LOGGER = Logger
			.getLogger(AccountDAOJDBCImpl.class);
	/**
	 * ComboPooledDataSource instance cpds.
	 */
	private static ComboPooledDataSource cpds = null;
	private static AccountDTO dbDTO = null;
	/**
	 * query string type for sql statement.
	 */
	private static String query = null;
	/**
	 * Connection instance conn.
	 */
	private transient Connection conn = null;
	/**
	 * preparedStatement(PreparedStatement is a interface accept value at run
	 * time.).
	 */
	private transient PreparedStatement preparedStatement = null;
	/**
	 * ResultSet(Select statement always return a ResultSet and ResultSet is
	 * interface also.).
	 */
	private transient ResultSet resultSet = null;

	/**
	 * This is a method to get instance of CPDS.
	 * 
	 * @return ComboPooledDataSource Instance
	 */
	static {
		LOGGER.debug("Debug: Now in Static block DCP in AccountDAOJDBCImpl");
		LOGGER
				.info("Debug:Now Reading datasource.properties from classpath---");
		try {
			cpds = new ComboPooledDataSource();
			/**
			 * JDBC Driver.
			 */
			cpds.setDriverClass(BUNDLE.getString("driver"));
			/**
			 * JDBC Url for DataBase.
			 */
			cpds.setJdbcUrl(BUNDLE.getString("jdbcUrl"));
			/**
			 * username of DataBase.
			 */
			cpds.setUser(BUNDLE.getString("username"));
			/**
			 * Password of DataBase.
			 */
			cpds.setPassword(BUNDLE.getString("password"));

			cpds.setInitialPoolSize(Integer.valueOf(BUNDLE
					.getString("initialPoolSize")));
			/**
			 * no. of connection increment by after pool rich limit but not
			 * after max pool size.
			 */
			cpds.setAcquireIncrement(Integer.valueOf(BUNDLE
					.getString("acquireIncrement")));
			/**
			 * Maximum no. of connection in pool.
			 */
			cpds.setMaxPoolSize(Integer
					.valueOf(BUNDLE.getString("maxPoolSize")));
			/**
			 * Minimum no. of connection in pool.
			 */
			cpds.setMinPoolSize(Integer
					.valueOf(BUNDLE.getString("minPoolSize")));
			LOGGER.info("Info:Data Connection pool is successfully Created");
		} catch (Exception e) {
			LOGGER.fatal("Error on creating DataConnectionPool", e);
		} finally {
			LOGGER.debug("Debug: Exit Static block DCP in AccountDAOJDBCImpl");
		}
	}

	/**
	 * This Method Demonstrate Insert Record(Account) in DataBase.
	 * 
	 * @param accountDTO
	 * @return void
	 * @exception DuplicateRecordException,JDBCException.
	 * 
	 */

	public void add(AccountDTO accountDTO) throws Exception {

		dbDTO = findByPK(accountDTO.getId());

		if (dbDTO == null) {

			try {
				LOGGER.debug("Debug:Now in Add(AccountDAOJDBCImpl)---");
				conn = cpds.getConnection();
				conn.setAutoCommit(false);
				System.out.println("In Addddd..................");
				query = "INSERT INTO account (id, balance, TYPE, openDate,lastAccessTime,lockSummery) VALUES(?, ?, ?, ?,?,?)";

				preparedStatement = conn.prepareStatement(query);
				preparedStatement.setInt(1, accountDTO.getId());
				preparedStatement.setDouble(2, accountDTO.getBalance());
				preparedStatement.setString(3, accountDTO.getType());
				preparedStatement.setDate(4, new Date(accountDTO.getOpenDate()
						.getTime()));
				preparedStatement.setTimestamp(5, new Timestamp(accountDTO
						.getLastAccessTime().getTime()));
				preparedStatement.setTimestamp(6, new Timestamp(accountDTO
						.getLockSummery().getTime()));
				preparedStatement.executeUpdate();
				conn.commit();
				LOGGER.debug("Record is Inserted Successfully...");
			} catch (Exception e) {
				conn.rollback();
				LOGGER.error("Error: In Add Methode....", e);
				throw new DatabaseException("Error: with Database Connection.."
						+ e);
			} finally {
				preparedStatement.close();
				conn.close();
				LOGGER.debug("Debug:Exit-- Add(AccountDAOJDBCImpl)---");
			}

		} else {
			LOGGER.error("This is Duplicate record for Id-" + dbDTO.getId());
			throw new DuplicateRecordException(
					"This is Duplicate record for Id-" + dbDTO.getId());
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

	public void delete(final AccountDTO accountDTO) throws Exception {
		try {
			LOGGER.debug("Debug:Now in Delete(AccountDAOJDBCImpl)");
			conn = cpds.getConnection();
			query = "delete from account where id=?";
			conn.setAutoCommit(false);
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, accountDTO.getId());
			preparedStatement.executeUpdate();
			conn.commit();
			LOGGER.debug("Record is deleted..");
		} catch (Exception e) {
			conn.rollback();
			LOGGER.error("Error: In Delete", e);
			throw new DatabaseException("Error with Database Connection" + e);

		} finally {
			preparedStatement.close();
			conn.close();
			LOGGER.debug("Debug:Exit-- Delete(AccountDAOJDBCImpl)---");
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

	public void update(final AccountDTO accountDTO) throws Exception {
		try {
			LOGGER.debug("Debug:Now In Update(AccountDAOJDBCImpl)---");
			conn = cpds.getConnection();
			query = "UPDATE account SET balance = ?, type=?, openDate = ?, lastAccessTime=?,lockSummery=? WHERE  id = ?";
			conn.setAutoCommit(false);

			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setDouble(1, accountDTO.getBalance());
			preparedStatement.setString(2, accountDTO.getType());
			preparedStatement.setDate(3, new Date(accountDTO.getOpenDate()
					.getTime()));
			preparedStatement.setTimestamp(4, new Timestamp(accountDTO
					.getLastAccessTime().getTime()));
			preparedStatement.setTimestamp(5, new Timestamp(accountDTO
					.getLockSummery().getTime()));
			preparedStatement.setInt(6, accountDTO.getId());
			preparedStatement.executeUpdate();
			conn.commit();
			LOGGER.debug("Record is Update.....");
		} catch (Exception e) {
			conn.rollback();
			LOGGER.error("Error:In Update Methode....", e);
			throw new DatabaseException("Error with DataBase Connection" + e);
		} finally {
			preparedStatement.close();
			conn.close();
			LOGGER.debug("Debug:Exit-- update(AccountDAOJDBCImpl)---");
		}
	}

	/**
	 * This Method Demonstrate Get Record(Account) from DataBase.
	 * 
	 * @param accountDTO
	 * @return accountDTO
	 * @exception DataBase
	 *                exception,JDBC Exception
	 * 
	 */

	public AccountDTO findByPK(final Integer id) throws Exception {
		dbDTO = null;
		try {
			LOGGER.debug("Debug:Now in FindByPk(AccountDAOJDBCImpl) method...");
			conn = cpds.getConnection();
			conn.setAutoCommit(false);
			query = "select * from account where  id=?";

			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				dbDTO = new AccountDTO();
				dbDTO.setId(resultSet.getInt(1));
				dbDTO.setBalance(resultSet.getDouble(2));
				dbDTO.setType(resultSet.getString(3));
				dbDTO.setOpenDate(resultSet.getDate(4));
				dbDTO.setLastAccessTime(resultSet.getTimestamp(5));
				dbDTO.setLockSummery(resultSet.getTimestamp(6));
			}
			conn.commit();
		} catch (Exception e) {
			LOGGER.error("Error in findByPk", e);
			throw new DatabaseException("Error in DataBase Connection" + e);
		} finally {
			preparedStatement.close();
			conn.close();
			LOGGER.debug("Debug:Exit-- FindByPk(AccountDAOJDBCImpl)---");
		}
		return dbDTO;
	}

	/**
	 * This Method Demonstrate Search Record(Account) from DataBase many type of
	 * individual criteria.
	 * 
	 * 
	 * @param accountDTO
	 * @return list
	 * @throws Exception
	 * @exception DataBaseException,JDBCException.
	 * 
	 */
	/*
	 * Search by Rahul Sahu
	 */
	public List<AccountDTO> search(final AccountDTO accountDTO)
			throws Exception {
		final List<AccountDTO> list = new ArrayList<AccountDTO>();
		try {
			LOGGER.debug("Debug:Now in Search(AccountDAOJDBCImpl)..");
			conn = cpds.getConnection();
			conn.setAutoCommit(false);

			/**
			 * This---where 1=1 if no where condition will be added then query
			 * will be executed...
			 * 
			 */
			query = "SELECT * FROM account WHERE 1 = 1";
			if (accountDTO.getId() != null) {
				query += " and  id='" + accountDTO.getId() + "'";
			}
			if (accountDTO.getBalance() != null) {
				query += " and  balance= '" + accountDTO.getBalance() + "'";
			}
			if (accountDTO.getType() != null) {
				query += " and  type like '" + accountDTO.getType() + '%' + "'";
			}
			if (accountDTO.getOpenDate() != null) {
				query += " and  openDate='" + accountDTO.getOpenDate() + "'";
			}
			preparedStatement = conn.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();

			final AccountDTO account = new AccountDTO();

			while (resultSet.next()) {
				account.setId(resultSet.getInt(1));
				account.setBalance(resultSet.getDouble(2));
				account.setType(resultSet.getString(3));
				account.setOpenDate(resultSet.getDate(4));
				accountDTO.setLastAccessTime(resultSet.getTimestamp(5));
				accountDTO.setLockSummery(resultSet.getTimestamp(6));

				list.add(account);
			}
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				LOGGER
						.error("Error:In Search SQL Exception....in RollBack"
								+ e);
			}
			LOGGER.error("Error:In Search" + e);
			throw new DatabaseException("Error: in DataBase Connection" + e);
		} finally {
			preparedStatement.close();
			conn.close();
			LOGGER.debug("Debug:Exit-- Search(AccountDAOJDBCImpl)---");
		}

		return list;
	}

}