package com.action;

import java.util.Date;

import com.delegate.UserBussinessDelegate;
import com.dto.UserDTO;
import com.exception.ApplicationException;
import com.exception.UserNotFoundException;

public class LoginAction extends BaseAction {

	private String login;
	private String password;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String execute() throws Exception {
		UserDTO dbDTO = new UserDTO();
		try {
			if ("Login".equals(operation)) {
				System.out.println(login);
				dbDTO = UserBussinessDelegate.authonticate(login, password);
				if (dbDTO != null) {
					System.out.println(dbDTO.getPassword() + "llllllllllll"
							+ dbDTO.getFirstName());
					session.put("user", dbDTO);
					if (dbDTO.getLastAccessTime() == null) {
						dbDTO.setLastAccessTime(new Date());
						UserBussinessDelegate.update(dbDTO);
						return "changepassword";
					}
					return "loggedIn";
				} else {
					addActionError("Please Enter valid Login and password");
					return "notLoggedIN";
				}
			}
		} catch (ApplicationException e) {
			System.out.println("hello2");
			addActionError("Please inter valid user id in application");
			return ERROR;
		} catch (UserNotFoundException e) {
			System.out.println("hello");
			addActionError("Please inter valid user id");
			return ERROR;
		}

		return SUCCESS;
	}

	@Override
	public String input() throws Exception {
		System.out.println("logout----------");
		if ("Logout".equals(operation)) {
			System.out.println("Logout if-----------");
			session.clear();

			System.out.println(session.get("user"));
			return "logout";
		}
		return INPUT;
	}

}
