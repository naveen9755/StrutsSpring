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

import com.dataConnectionPool.DataConnectionPool;
import com.dto.UserDTO;
import com.exception.DatabaseException;
import com.exception.DuplicateRecordException;
import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * This class Demonstrate UserDAOJDBCImpl.
 * 
 * @author Chandrabhan
 * @version 1.1
 * 
 */
public class UserDAOJDBCImpl implements UserDAOInt {
	/**
	 * Attribute -Logger to record log in file.
	 */
	private static final Logger LOGGER = Logger
			.getLogger(UserDAOJDBCImpl.class);
	/**
	 * Attribute-DB_EXCEPTION type String is message used in custom exception.
	 */
	private static final String DB_EXCEPTION = "Error with DataBase Connection.";
	/**
	 * ComboPooledDataSource instance cpds.
	 */

	private static ComboPooledDataSource cpds = null;
	/**
	 * Attribute-query type- StringBuffer is used in sql query.
	 */
	private static StringBuffer query = null;
	/**
	 * Connection instance conn.
	 */
	private transient Connection conn = null;
	/**
	 * PreparedStatement instance preparedStatement.
	 */
	private transient PreparedStatement preparedStatement = null;
	/**
	 * ResultSet instance resultSet.
	 */
	private transient ResultSet resultSet = null;

	private static UserDTO userDTO = null;

	/**
	 * This is static variable of DataConnectionPool type.
	 * 
	 */
	private static DataConnectionPool dcp;
	/**
	 * Resource bundle to get value life Driver info, DataBaseName, username,
	 * password, no of connection,MAX pool Size etc at runtime from non-java
	 * file.
	 * 
	 */

	private static final ResourceBundle BUNDLE = ResourceBundle
			.getBundle("com.bundle.ResourceBundle");

	/**
	 * This is a method to get instance of CPDS.
	 * 
	 * @return ComboPooledDataSource Instance
	 */
	static {
		LOGGER
				.debug("Debug:Now Reading datasource.properties from classpath---");
		try {
			cpds = new ComboPooledDataSource();
			/**
			 * JDBC Driver.
			 */
			cpds.setDriverClass(BUNDLE.getString("driver"));
			/**
			 * JDBC Url for Local host.
			 */
			cpds.setJdbcUrl(BUNDLE.getString("jdbcUrl"));
			/**
			 * DataBase username.
			 */
			cpds.setUser(BUNDLE.getString("username"));
			/**
			 * DataBase password.
			 */
			cpds.setPassword(BUNDLE.getString("password"));
			/**
			 * Initial poolSize.
			 */
			cpds.setInitialPoolSize(Integer.valueOf(BUNDLE
					.getString("initialPoolSize")));
			/**
			 * Contection increment by.
			 */
			cpds.setAcquireIncrement(Integer.valueOf(BUNDLE
					.getString("acquireIncrement")));
			/**
			 * Maximum no of connection in pool.
			 */
			cpds.setMaxPoolSize(Integer
					.valueOf(BUNDLE.getString("maxPoolSize")));
			/**
			 * Minimum pooloSize.
			 */
			cpds.setMinPoolSize(Integer
					.valueOf(BUNDLE.getString("minPoolSize")));
			LOGGER.info("Info: DCP is Successfully Created.");

		} catch (Exception e) {
			LOGGER.fatal("Fatal:Error on creating DataConnectionPool", e);
		} finally {
			LOGGER.debug("Debug:Exit from creating DataConnectionPool");
		}
	}

	/**
	 * This Method Demonstrate add record Logics
	 * 
	 * @param userDTO
	 *            which is inserted in database.
	 * @return void
	 * 
	 */
	public void add(UserDTO userDTO) throws Exception {

		LOGGER.debug("Debug:Now in add() UserDAOJDBCImpl---");

		final UserDTO dbDTO = findByPk(userDTO.getId());

		if (dbDTO == null) {
			try {
				conn = cpds.getConnection();
				conn.setAutoCommit(false);
				preparedStatement = conn
						.prepareStatement("insert into user values(?,?,?,?,?,?,?,?)");
				preparedStatement.setInt(1, userDTO.getId());
				preparedStatement.setString(2, userDTO.getFirstName());
				preparedStatement.setString(3, userDTO.getLastName());
				preparedStatement.setString(4, userDTO.getLogin());
				preparedStatement.setString(5, userDTO.getPassword());
				preparedStatement.setDate(6, new Date(userDTO.getDateOfBirth()
						.getTime()));
				preparedStatement.setTimestamp(7, new Timestamp(userDTO
						.getLastAccessTime().getTime()));
				preparedStatement.setTimestamp(8, new Timestamp(userDTO
						.getLockSummery().getTime()));
				preparedStatement.executeUpdate();
				conn.commit();
				LOGGER.debug("Debug:Record is Added   UserDAOJDBCImpl---");
			} catch (Exception e) {
				conn.rollback();
				LOGGER.error("Error:Now in Add UserDAOJDBCImpl---", e);
				throw new DatabaseException(DB_EXCEPTION + e);
			} finally {
				preparedStatement.close();
				conn.close();
				LOGGER.debug("Debug:Exit from add() UserDAOJDBCImpl...... ");
			}
		} else {
			LOGGER.debug("Debug:This is a duplicate entry for key "
					+ userDTO.getId());
			throw new DuplicateRecordException(
					"This is a duplicate entry for key " + userDTO.getId());
		}

	}

	/**
	 * This Method Demonstrate updation logics of user.
	 * 
	 * @param userDTO
	 *            instance which one is update in databse.
	 * @return void
	 * 
	 */
	public void update(UserDTO userDTO) throws Exception {

		try {
			LOGGER.debug("Debug:Now in Update UserDAOJDBCImpl---");
			conn = cpds.getConnection();
			conn.setAutoCommit(false);
			preparedStatement = conn
					.prepareStatement("update user set firstName=?,lastName=?,login=?,password=?,dateOfBirth=?,lastAccessTime=?,lockSummery=?  where id =?");
			preparedStatement.setString(1, userDTO.getFirstName());
			preparedStatement.setString(2, userDTO.getLastName());
			preparedStatement.setString(3, userDTO.getLogin());
			preparedStatement.setString(4, userDTO.getPassword());
			preparedStatement.setDate(5, new Date(userDTO.getDateOfBirth()
					.getTime()));
			preparedStatement.setTimestamp(6, new Timestamp(userDTO
					.getLastAccessTime().getTime()));
			preparedStatement.setTimestamp(7, new Timestamp(userDTO
					.getLockSummery().getTime()));
			preparedStatement.setInt(8, userDTO.getId());

			preparedStatement.executeUpdate();
			conn.commit();
		} catch (Exception exception) {
			conn.rollback();
			LOGGER.error("Error: In Update  UserDAOJDBCImpl", exception);
			throw new DatabaseException(DB_EXCEPTION + exception);
		} finally {
			preparedStatement.close();
			conn.close();
			LOGGER.debug("Debug:Exit Update() UserDAOJDBCImpl---");
		}
	}

	/**
	 * This Method Demonstrate delete logics of UserDTO.
	 * 
	 * @param userDTO
	 *            instance which is deleted from database.
	 * @return void
	 * 
	 */

	public void delete(UserDTO userDTO) throws Exception {

		try {
			LOGGER.debug("Debug:Now in Delete UserDAOJDBCImpl----");
			conn = cpds.getConnection();
			conn.setAutoCommit(false);
			preparedStatement = conn
					.prepareStatement("delete from user where id =?");
			preparedStatement.setInt(1, userDTO.getId());
			preparedStatement.executeUpdate();
			preparedStatement.close();
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			LOGGER.error("Error:Now In Delete UserDAOJDBCImpl---", e);
			throw new DatabaseException(DB_EXCEPTION + e);

		} finally {
			preparedStatement.close();
			conn.close();
			LOGGER.error("Error:Exit Delete() UserDAOJDBCImpl---");
		}
	}

	/**
	 * This Method Demonstrate FindByPK of UserDTO.
	 * 
	 * @param userId
	 *            type Integer which is get from database.
	 * @return UserDTO
	 * 
	 * 
	 */

	public UserDTO findByPk(Integer id) throws Exception {
		try {
			LOGGER.debug("Debuge:Now In findByPk UserDAOJDBCImpl---");
			conn = cpds.getConnection();
			conn.setAutoCommit(false);
			preparedStatement = conn
					.prepareStatement("select * from user where id ='" + id
							+ "'");

			resultSet = preparedStatement.executeQuery();
			userDTO = null;
			while (resultSet.next()) {
				userDTO = new UserDTO();
				userDTO.setId(resultSet.getInt(1));
				userDTO.setFirstName(resultSet.getString(2));
				userDTO.setLastName(resultSet.getString(3));
				userDTO.setLogin(resultSet.getString(4));
				userDTO.setPassword(resultSet.getString(5));
				userDTO.setDateOfBirth(resultSet.getDate(6));
				userDTO.setLastAccessTime(resultSet.getTimestamp(7));
				userDTO.setLockSummery(resultSet.getTimestamp(8));
			}
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			LOGGER.error("Error in findByPK", e);
			throw new DatabaseException(DB_EXCEPTION + e);
		} finally {
			preparedStatement.close();
			conn.close();
			LOGGER.debug("Debug: Exit findByPk UserDAOJDBCImpl");
		}
		return userDTO;
	}

	/**
	 * 
	 * This Method Demonstrate Search logics of user.
	 * 
	 * @return list that Contain UserDTO.
	 * @param userDTO
	 *            instance.
	 * 
	 * @Authore--- by Rahul Sahu
	 */
	public List<UserDTO> search(UserDTO userDTO) throws Exception {
		List<UserDTO> list = new ArrayList<UserDTO>();
		try {
			LOGGER.debug("Debug:Now in Search UserDAOJDBCImpl---");
			conn = cpds.getConnection();
			conn.setAutoCommit(false);
			/**
			 * where 1=1 if no where condition will be added then query will be
			 * 
			 * executed...
			 */
			query = new StringBuffer();
			query.append("SELECT * FROM user WHERE 1 = 1");

			if (userDTO.getFirstName() != null) {
				query.append(" and firstName like '" + userDTO.getFirstName()
						+ "%'");
			}

			if (userDTO.getLastName() != null) {
				query
						.append(" and lastName like '" + userDTO.getLogin()
								+ "%'");
			}

			if (userDTO.getLogin() != null) {
				query.append(" and login like '" + userDTO.getLogin() + "%'");
			}

			if (userDTO.getDateOfBirth() != null) {
				query.append(" and dob = '" + userDTO.getDateOfBirth() + "'");
			}
			preparedStatement = conn.prepareStatement(query.toString());

			resultSet = preparedStatement.executeQuery();
			userDTO = null;
			while (resultSet.next()) {
				userDTO = new UserDTO();
				userDTO.setId(resultSet.getInt(1));
				userDTO.setFirstName(resultSet.getString(2));
				userDTO.setLastName(resultSet.getString(3));
				userDTO.setLogin(resultSet.getString(4));
				userDTO.setPassword(resultSet.getString(5));
				userDTO.setDateOfBirth(resultSet.getDate(6));
				userDTO.setLastAccessTime(resultSet.getTimestamp(7));
				userDTO.setLockSummery(resultSet.getTimestamp(7));
				list.add(userDTO);
			}
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			LOGGER.error("Error:In search() UserDAOJDBCImpl", e);
			throw new DatabaseException(DB_EXCEPTION + e);
		} finally {
			preparedStatement.close();
			conn.close();
			LOGGER.debug("Debug:Exit search() UserDAOJDBCImpl");
		}
		return list;
	}
	/**
	 * This is nextpk used when we use NPK
	 * @return integer
	 * @throws SQLException
	 */

	public int nextPK() throws SQLException {
		int next;
		try {
			LOGGER.debug("Debug: Now in NextPK UserDAOJDBCImpl----");
			conn = cpds.getConnection();
			conn.setAutoCommit(false);
			preparedStatement = conn
					.prepareStatement("SELECT MAX(id)FROM user");
			resultSet = preparedStatement.executeQuery();
			next = 1;
			if (resultSet.next()) {
				next += resultSet.getInt(1);
			}
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				LOGGER.error("Error:In search() UserDAOJDBCImpl", e);
			}
			LOGGER.error("Error in NextPK", e);
			throw new DatabaseException(DB_EXCEPTION + e);
		} finally {
			preparedStatement.close();
			conn.close();
			LOGGER.debug("Debug:Exit search() UserDAOJDBCImpl ");
		}
		return next;
	}
	
	

	public List<UserDTO> getList() throws Exception {
		List<UserDTO> list = new ArrayList<UserDTO>();
		try {
			LOGGER.debug("Debug:Now in getList UserDAOJDBCImpl---");
			conn = cpds.getConnection();
			conn.setAutoCommit(false);
			/**
			 * where 1=1 if no where condition will be added then query will be
			 * 
			 * executed...
			 */
			query = new StringBuffer();
			query.append("SELECT * FROM user WHERE 1 = 1");

			preparedStatement = conn.prepareStatement(query.toString());

			resultSet = preparedStatement.executeQuery();
			userDTO = null;
			while (resultSet.next()) {
				userDTO = new UserDTO();
				userDTO.setId(resultSet.getInt(1));
				userDTO.setFirstName(resultSet.getString(2));
				userDTO.setLastName(resultSet.getString(3));
				userDTO.setLogin(resultSet.getString(4));
				userDTO.setPassword(resultSet.getString(5));
				userDTO.setDateOfBirth(resultSet.getDate(6));
				userDTO.setLastAccessTime(resultSet.getTimestamp(7));
				userDTO.setLockSummery(resultSet.getTimestamp(7));
				list.add(userDTO);
			}
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			LOGGER.error("Error:In search() UserDAOJDBCImpl", e);
			throw new DatabaseException(DB_EXCEPTION + e);
		} finally {
			preparedStatement.close();
			conn.close();
			LOGGER.debug("Debug:Exit search() UserDAOJDBCImpl");
		}
		return list;
	}

	public List getsecurityQuestion() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}