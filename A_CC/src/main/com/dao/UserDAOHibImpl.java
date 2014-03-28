package com.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.dto.UserDTO;
import com.exception.DatabaseException;
import com.exception.DuplicateRecordException;

/**
 * This class Demonstrate UserDAOHibImpl.
 * 
 * @author Chandrabhan
 * @version 1.1
 * 
 */
public class UserDAOHibImpl implements UserDAOInt {
	/**
	 * Attribute -Logger to record log in log file.
	 */
	private static final Logger LOGGER = Logger
			.getLogger(AccountDAOJDBCImpl.class);
	/**
	 * SessionFactory
	 */
	private static SessionFactory sessionFactory = new Configuration()
			.configure().buildSessionFactory();
	/**
	 * Session
	 */
	private transient Session session = null;
	/**
	 * Transaction
	 */
	private transient Transaction transaction = null;

	private static UserDTO userDTO = null;
	/**
	 * Criteria
	 */
	private static Criteria criteria = null;
	/**
	 * Attribute-DB_EXCEPTION type String is message used in custom exception.
	 */
	private static final String DB_EXCEP = "Error:Error with DataBase Connection";

	/**
	 * This Method Demonstrate add record Logics
	 * 
	 * @param userDTO
	 *            which is inserted in database.
	 * @return void
	 * 
	 */
	public void add(UserDTO userDTO) throws Exception {
		LOGGER.debug("Debug:Now In Add()...UserDAOHibImpl.");
		UserDTO userDTO2 = new UserDTO();
		userDTO2.setLogin(userDTO.getLogin());
		List list = search(userDTO2);
		// UserDTO dbDTO = findByPk(userDTO.getId());
		if (list.size() == 0) {

			try {
				session = sessionFactory.openSession();
				transaction = session.beginTransaction();
				session.save(userDTO);
				transaction.commit();
			} catch (Exception e) {
				transaction.rollback();
				System.out.println("CCC" + e);
				LOGGER.error("Error: In UserDAOHibIml add()", e);
				throw new DatabaseException(DB_EXCEP + e);
			} finally {
				session.close();
				LOGGER.debug("Debug:Exit add()....UserDAOHibImpl.");
			}
		} else {
			LOGGER.error("Error:This is Duplicate Record add(userDAOHibImpl)"
					+ userDTO.getId());
			System.out.println("Duplicate record Exception");
			throw new DuplicateRecordException(
					"This is Duplicate Record for id:" + userDTO.getId());
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
		LOGGER.debug("Now In delete()...");
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.delete(userDTO);
			transaction.commit();

		} catch (Exception e) {
			transaction.rollback();
			LOGGER.error(DB_EXCEP, e);
			throw new DatabaseException(DB_EXCEP + e);
		} finally {
			session.close();
			LOGGER.info("Debug:Exit Delete()... UserDAOHibImpl.");
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
		LOGGER.debug("Debug:Now In update()...");
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.update(userDTO);
			transaction.commit();
		} catch (Exception e) {
			LOGGER.error(DB_EXCEP + e);
		} finally {
			session.close();
			LOGGER.debug("Debug:Exit Update(UserDAOHibImpl)");
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
			LOGGER.debug("Now In findByPk()...");
			session = sessionFactory.openSession();
			userDTO = null;
			userDTO = (UserDTO) session.get(UserDTO.class, id);
		} catch (Exception e) {
			LOGGER.error(DB_EXCEP + e);
		} finally {
			session.close();
			LOGGER.debug("Debug:Exit findByPk(UserDAOHibImpl)");
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
	 */
	public List<UserDTO> search(UserDTO userDTO) {
		List<UserDTO> list = new ArrayList<UserDTO>();
		LOGGER.debug("Debug:Now In search()...UserDAOHibImpl");
		try {

			session = sessionFactory.openSession();

			criteria = session.createCriteria(UserDTO.class);

			if (userDTO.getId() != null) {
				criteria.add(Restrictions.eq("id", userDTO.getId()));

			}
			if (userDTO.getFirstName() != null) {

				criteria.add(Restrictions.like("firstName", userDTO
						.getFirstName() + '%'));

			}
			if (userDTO.getLastName() != null) {

				criteria.add(Restrictions.like("lastName", userDTO
						.getLastName() + '%'));

			}
			if (userDTO.getLogin() != null) {

				criteria.add(Restrictions.ilike("login", userDTO.getLogin(),
						MatchMode.EXACT));

			}
			if (userDTO.getPassword() != null) {

				criteria.add(Restrictions.ilike("password", userDTO
						.getPassword(), MatchMode.EXACT));

			}
			if (userDTO.getDateOfBirth() != null) {

				criteria.add(Restrictions.eq("dateOfBirth", userDTO
						.getDateOfBirth()));

			}

			list = criteria.list();

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(DB_EXCEP + e);
		} finally {
			session.close();
			LOGGER.debug("Debug:Exit Search(UserDAOHibImpl)");
		}
		return list;
	}

	public List<UserDTO> getList() throws Exception {
		List<UserDTO> list = new ArrayList<UserDTO>();
		LOGGER.debug("Debug:Now In getlist()...UserDAOHibImpl");
		try {

			session = sessionFactory.openSession();

			criteria = session.createCriteria(UserDTO.class);

			list = criteria.list();

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(DB_EXCEP + e);
		} finally {
			session.close();
			LOGGER.debug("Debug:Exit getList(UserDAOHibImpl)");
		}
		return list;

	}

	public List getsecurityQuestion() throws Exception {
		sessionFactory = new Configuration().configure().buildSessionFactory();
		session = sessionFactory.openSession();
		Query query = session
				.createSQLQuery("select question from securityquestion");
		List list = query.list();
		return list;
	}
}
