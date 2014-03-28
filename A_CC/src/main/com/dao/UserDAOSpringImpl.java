package com.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dto.UserDTO;
import com.exception.DatabaseException;
import com.exception.DuplicateRecordException;

/**
 * This class Demonstrate UserDAOSpringImpl.
 * 
 * @author Chandrabhan
 * @version 1.1
 * 
 */
public class UserDAOSpringImpl extends HibernateDaoSupport implements
		UserDAOInt {
	/**
	 * Logger to write log messages in log file.
	 */
	private static final Logger LOGGER = Logger
			.getLogger(AccountDAOJDBCImpl.class);
	/**
	 * DetachedCriteria
	 */

	private static DetachedCriteria criteria = null;

	/**
	 * Attribute-DB_EXCEPTION type String is message used in custom exception.
	 */
	private static final String DB_EXCP = "Error with DataBase Connection";

	private transient UserDTO userDTO = null;

	/**
	 * This Method Demonstrate add record Logics
	 * 
	 * @param userDTO
	 * @return void
	 * 
	 */

	public void add(UserDTO userDTO) throws Exception {
		LOGGER.debug("Debug:Now in Add UserDAOHibImpl----");

		UserDTO dbDTO = findByPk(userDTO.getId());
		if (dbDTO == null) {
			try {
				getHibernateTemplate().save(userDTO);
			} catch (Exception e) {
				LOGGER.error("Error:In add user...", e);
				throw new DatabaseException(DB_EXCP + e);
			}
		} else {
			LOGGER.debug("Debug:This is Duplicate record for id"
					+ userDTO.getId());
			throw new DuplicateRecordException("This is Duplicate Record"
					+ dbDTO.getId());
		}

	}

	/**
	 * This Method Demonstrate delete logics of UserDTO.
	 * 
	 * @param userDTO
	 *            instance
	 * @return void
	 * 
	 */

	public void delete(UserDTO userDTO) throws Exception {
		LOGGER.debug("Debug:Now In delete UserDAOSpringImpl...");
		try {
			getHibernateTemplate().delete(userDTO);
		} catch (Exception e) {
			LOGGER.error("Error:In Delete User....", e);
			throw new DatabaseException(DB_EXCP + e);
		} finally {
			LOGGER.debug("Debug:Exit delete() UserDAOSpringImpl...");
		}
	}

	/**
	 * This Method Demonstrate updation logics of user.
	 * 
	 * @param userDTO
	 *            instance
	 * @return void
	 * 
	 */

	public void update(UserDTO userDTO) throws Exception {
		LOGGER.debug("Debug:Now In update() UserDAOSpringImpl---");
		try {
			getHibernateTemplate().update(this.userDTO);
		} catch (Exception e) {
			LOGGER.error("Error:In Update User.....", e);
			throw new DatabaseException(DB_EXCP + e);
		} finally {
			LOGGER.debug("Debug:Exit update() UserDAOSpringImpl---");
		}
	}

	/**
	 * This Method Demonstrate FindByPK of UserDTO.
	 * 
	 * @param userId
	 *            type Integer
	 * @return UserDTO
	 * 
	 * 
	 */

	public UserDTO findByPk(Integer id) {
		LOGGER.debug("Debug:Now in findByPk UserDAOHibImpl---");
		try {
			userDTO = new UserDTO();
			userDTO = (UserDTO) getHibernateTemplate().get(UserDTO.class, id);
		} catch (Exception e) {
			LOGGER.error("Error:In findByPk User", e);
			throw new DatabaseException(DB_EXCP + e);
		} finally {
			LOGGER.debug("Debug:Exit findByPk UserDAOHibImpl---");
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

	public List<UserDTO> search(UserDTO userDTO) throws Exception {
		LOGGER.debug("Debug:Now in search UserDAOSpringImpl");
		List<UserDTO> list = new ArrayList<UserDTO>();
		try {

			criteria = DetachedCriteria.forClass(UserDTO.class);

			if (userDTO.getId() != null) {
				criteria.add(Restrictions.eq("userId", userDTO.getId()));
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
				criteria.add(Restrictions.like("login", userDTO.getLogin(),
						MatchMode.EXACT));
			}

			if (userDTO.getPassword() != null) {
				criteria.add(Restrictions.like("password", userDTO
						.getPassword(), MatchMode.EXACT));
			}

			if (userDTO.getDateOfBirth() != null) {
				criteria.add(Restrictions.like("dob", new Date(userDTO
						.getDateOfBirth().getTime())));
			}
			list = getHibernateTemplate().findByCriteria(criteria);
		} catch (Exception e) {
			LOGGER.error("Error:In Serach User...", e);
			throw new DatabaseException(DB_EXCP + e);
		} finally {
			LOGGER.debug("Debug:Exit search UserDAOSpringImpl");
		}

		return list;
	}

	public List<UserDTO> getList() throws Exception {
		LOGGER.debug("Debug:Now in getList UserDAOSpringImpl");
		List<UserDTO> list = new ArrayList<UserDTO>();
		try {

			criteria = DetachedCriteria.forClass(UserDTO.class);

			list = getHibernateTemplate().findByCriteria(criteria);
		} catch (Exception e) {
			LOGGER.error("Error:In Serach User...", e);
			throw new DatabaseException(DB_EXCP + e);
		} finally {
			LOGGER.debug("Debug:Exit getList UserDAOSpringImpl");
		}

		return list;
	}

	public List getsecurityQuestion() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}