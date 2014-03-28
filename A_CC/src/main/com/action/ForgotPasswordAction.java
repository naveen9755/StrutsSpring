package com.action;

import java.util.Date;
import java.util.List;

import com.delegate.EmailBussinessDelegate;
import com.delegate.UserBussinessDelegate;
import com.dto.UserDTO;

public class ForgotPasswordAction extends BaseAction {

	private String login;
	private List securityList;
	private String securityQuestion;
	private String answer;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public List getSecurityList() {
		return securityList;
	}

	public void setSecurityList(List securityList) {
		this.securityList = securityList;
	}

	public String getSecurityQuestion() {
		return securityQuestion;
	}

	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Override
	public String execute() throws Exception {
		UserDTO userDTO = new UserDTO();
		if ("ForgotPassword".equals(operation)) {
			System.out.println("In Forget>>>>>>>>>>");
			userDTO.setLogin(login);
			List list = UserBussinessDelegate.search(userDTO);
			System.out.println("Size of list-------");
			if (list.size() == 1) {
				UserDTO dbDTO = (UserDTO) list.get(0);
				if (login.equals(dbDTO.getLogin())
						&& securityQuestion.equals(dbDTO.getSecurityQuestion())
						&& answer.equals(dbDTO.getAnswer())) {
					System.out.println("In Mail......");
					EmailBussinessDelegate.sendMail(dbDTO.getLogin(),
							"Your password", "Password is-----"
									+ dbDTO.getPassword());
					addActionError("You password is send on your email id");
					return "passwordSend";
				} else {
					System.out.println("In Not mail................");
					addActionError("Please Enter Valid Login Id");
					return "invalidEmailId";
				}
			}
		}

		return SUCCESS;
	}

	@Override
	public String input() throws Exception {
		return INPUT;
	}

	@Override
	public void prepare() throws Exception {
		securityList = UserBussinessDelegate.getSecurityQuestion();
	}

}
