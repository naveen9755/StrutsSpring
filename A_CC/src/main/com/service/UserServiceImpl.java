package com.service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.dao.UserDAOInt;
import com.dto.UserDTO;
import com.emailService.MailServiceInt;
import com.exception.ApplicationException;
import com.exception.DatabaseException;
import com.exception.DuplicateRecordException;
import com.exception.UserNotFoundException;
import com.factory.DAOFactory;
import com.locator.ServiceLocator;

/**
 * This class have all bussiness logices for user.
 * 
 * @author Chandrabhan
 * @version 1.0
 */
public class UserServiceImpl implements UserServiceInt {
	/**
	 * LOGGER to record log message to log file.
	 */
	private static Logger LOGGER = Logger.getLogger(UserServiceImpl.class);
	/**
	 * Creating DAO instance with help of DAOFactory.
	 */
	private UserDAOInt userDAO = DAOFactory.getInstance().getUser();
	/**
	 * Creating UserDTO instance.
	 */
	private UserDTO dbDTO = new UserDTO();
	/**
	 * Get MailService Instance Form ServiceLocator
	 */
	private MailServiceInt mailService = ServiceLocator.getInstance()
			.getMailService();
	/**
	 * Accessing EssensialInformation From Resource Boundle
	 */
	private ResourceBundle bundle = ResourceBundle
			.getBundle("com.bundle.emailMessage");
	private String subject = bundle.getString("emailSubject");
	private String message = bundle.getString("emailMessage");

	/**
	 * This method demonstrat add method of service class which is responsible
	 * to add user account in data base with the help of DAO.
	 * 
	 * @param userDTO
	 *            Class instance
	 * @return userId-Type-long
	 */
	public Long add(UserDTO userDTO) throws Exception {
		LOGGER.debug("Debug:Now in add() userServiceImpl");
		long id = 0;
		try {
			userDAO.add(userDTO);
			List list = userDAO.search(userDTO);
			if (list.size() > 0) {
				dbDTO = (UserDTO) list.get(0);
				id = dbDTO.getId();
			} else {
				LOGGER
						.error("Error:UserNotFount Exception in add userServieImpl");
				throw new UserNotFoundException("Error: UserNotFound Exception");
			}
			LOGGER.debug("Debug:Now in Email");
			// mailService.sendMail(userDTO.getLogin(), subject,
			// message+userDTO.getPassword());
		} catch (DuplicateRecordException e) {
			LOGGER.error("Error:This is DuplicateRecord" + e);

		} catch (Exception e) {
			LOGGER
					.error("Error:Their is an application exception in add UserServiceImpl");
			throw new ApplicationException(
					"Error:Their is an application exception in add UserServiceImpl"
							+ e);
		}

		return id;
	}

	/**
	 * This method demonstrat get record with the help of uniqe id
	 * 
	 * @param this
	 *            method take userId type of Integer.
	 * @return instance of userDTO Class
	 */
	public UserDTO get(Integer userId) throws Exception {
		LOGGER.debug("Debug:Now in get() userServiceImpl");
		try {
			dbDTO = userDAO.findByPk(userId);
		} catch (Exception e) {
			throw new UserNotFoundException(
					"Error:UserNotFound Exception in get UserServiceImpl" + e);
		}
		return dbDTO;
	}

	/**
	 * This method demonstrat update record method in service class and this
	 * method is responsible for update record in database with the help of DAO.
	 * 
	 * @param instance
	 *            of userDTO class
	 * @return instance of userDTO class
	 */

	public UserDTO update(UserDTO userDTO) throws Exception {
		LOGGER.debug("Debug:Now in update() userServiceImpl");
		try {
			dbDTO = get(userDTO.getId());
			if (dbDTO != null && dbDTO.getId().equals(userDTO.getId())) {
				userDAO.update(userDTO);
				dbDTO = userDAO.findByPk(userDTO.getId());
			}
		} catch (Exception e) {
			LOGGER
					.error("Error: Their is an ApplicationException in update UserServiceImp"
							+ e);
			throw new ApplicationException(
					"Error: Their is an ApplicationException in update UserServiceImp"
							+ e);
		}
		return dbDTO;
	}

	/**
	 * This is authonticate method of service class which is responsible for
	 * user athountication with the help of search method of DAO.
	 * 
	 * @param login
	 *            and password both are string type
	 * @return instance of userDTO class
	 */
	public UserDTO authonticate(String login, String password) throws Exception {
		LOGGER.debug("Debug:Now in authonticate() userServiceImpl");
		UserDTO userDTO = null;
		dbDTO = null;
		boolean b = false;
		try {
			int count = 0;
			dbDTO = new UserDTO();
			dbDTO.setLogin(login);
			List list = userDAO.search(dbDTO);
			// dbDTO = (UserDTO) list.get(0);
			if (list.size() > 0) {
				userDTO = new UserDTO();
				userDTO.setLogin(login);
				userDTO.setPassword(password);
				List authonticateList = userDAO.search(userDTO);
				if (authonticateList.size() == 1) {
					userDTO = (UserDTO) authonticateList.get(0);
					System.out.println(userDTO.getFirstName() + "\t"
							+ userDTO.getLastName() + "\t" + userDTO.getLogin()
							+ "\t" + userDTO.getPassword());
					if (userDTO.getCount() <= 3) {
						System.out.println("1");
						b = lockUser(userDTO);
					}
					if (b) {
						System.out.println("2");
						return userDAO.findByPk(userDTO.getId());
					} else {
						System.out.println("3");
						System.out.println("You are lock for 24 houre"
								+ userDTO.getLockSummery());
						return userDTO = null;
					}
				} else {
					count = dbDTO.getCount();
					if (count < 2) {
						System.out.println("4");
						count = count + 1;
						System.out.println("Else-----" + count);
						dbDTO.setCount(count);
						userDAO.update(dbDTO);
					} else if (count == 2) {
						System.out.println("5");
						Date todayDate = new Date();
						todayDate.setDate(todayDate.getDate() + 1);
						Calendar calendar = Calendar.getInstance();
						System.out.println(calendar.getTime());
						calendar.setTime(new Date());
						count = count + 1;
						dbDTO.setCount(count);
						dbDTO.setLockSummery(todayDate);
						userDAO.update(dbDTO);

					}
				}
			} else {

				LOGGER
						.error("Error:UserNotFound in authonticate userServiceimpl");
				throw new UserNotFoundException(
						"Error:UserNotFound in authonticate userServiceimpl");

			}
		} catch (UserNotFoundException e) {
			LOGGER.error("Error:UserNotFound in authonticate userServiceimpl");
			throw new UserNotFoundException(
					"Error:UserNotFound in authonticate userServiceimpl");
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER
					.error("Error:ApplicationException in userServiceImpl authonticate()"
							+ e);
			throw new ApplicationException(
					"Error:ApplicationException in userServiceImpl authonticate()"
							+ e);

		}
		userDTO = null;
		return userDTO;
	}

	/**
	 * This is search method
	 * 
	 * @param instance
	 *            of userDTO class
	 * @return list that contain instance of userDTO class
	 */
	public List search(UserDTO userDTO) throws Exception {
		LOGGER.debug("Debug:Now in search() userServiceImpl");
		return userDAO.search(userDTO);
	}

	/**
	 * This is changepassword method of Service class.
	 * 
	 * @param userId-type-Integer,Old
	 *            and new password both are type - String.
	 * @return boolean value that is use to identify that password is change of
	 *         not.
	 */

	public Boolean changePassword(Integer userId, String oldPassword,
			String newPassword) throws Exception {
		LOGGER.debug("Debug:Now in changePassword() userServiceImpl");
		boolean b = false;
		UserDTO userDTO = new UserDTO();
		userDTO = userDAO.findByPk(userId);
		if (userDTO != null && userId.equals(userDTO.getId())
				&& oldPassword.equals(userDTO.getPassword())) {
			userDTO.setPassword(newPassword);
			userDAO.update(userDTO);
			b = true;
			LOGGER
					.debug("Debug:Password successfully change"
							+ userDTO.getId());
		} else {
			b = false;
			LOGGER.debug("Debug:Password is not changed" + userDTO.getId());
		}
		return b;
	}

	/**
	 * This method demonstrat LockUser
	 * 
	 * @param instance
	 *            of userDTO
	 * @return boolean type variable
	 */

	public Boolean lockUser(UserDTO userDTO) throws Exception {
		LOGGER.debug("Debug:Now in lockUser(UserDTO userDTO) userServiceImpl");
		boolean b = false;

		userDTO = userDAO.findByPk(userDTO.getId());

		Date lastAccessDate = userDTO.getLastAccessTime();
		GregorianCalendar currentTime = new GregorianCalendar();
		GregorianCalendar lockSummary = new GregorianCalendar();
		lockSummary.setTime(userDTO.getLockSummery());
		Date todayDate = new Date();
		if (userDTO.getCount() <= 3
				&& currentTime.getTimeInMillis() >= lockSummary
						.getTimeInMillis()) {
			userDTO.setCount(0);
			userDTO.setLockSummery(new Date());
			userDAO.update(userDTO);

			b = true;
		}
		return b;
	}

	public UserDTO updateAccess(UserDTO userDTO) throws Exception {
		LOGGER.debug("updateAccess start in UserServiceSpringJBImp");
		try {
			dbDTO = userDAO.findByPk(userDTO.getId());
			LOGGER.debug("before change role: " + userDTO.getRole());
			dbDTO.setRole(userDTO.getRole());
			userDAO.update(dbDTO);
		} catch (DatabaseException e) {
			LOGGER
					.error("ApplicationException in UserServiceJavaBeanImp updateAccess()");
			throw new ApplicationException(
					"ApplicationException in UserServiceJavaBeanImp updateAccess()");
		}
		LOGGER.debug("After change role: " + userDTO.getRole());

		return dbDTO;
	}

	/**
	 * Get SecurityQuestion
	 * 
	 * @return list
	 */
	public List getSecurityQuestion() throws Exception {
		return userDAO.getsecurityQuestion();
	}

	public List<UserDTO> getList() throws Exception {
		return userDAO.getList();
	}

	public void delete(Integer id) throws Exception {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(id);
		userDAO.delete(userDTO);

	}

}
