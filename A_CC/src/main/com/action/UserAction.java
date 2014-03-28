package com.action;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.delegate.UserBussinessDelegate;
import com.dto.UserDTO;

public class UserAction extends BaseAction {
	private String firstName;
	private String lastName;
	private String login;
	private String password;
	private File image;
	private String mobileNumber;
	private Date dateOfBirth;
	private String securityQuestion;
	private String answer;
	private List securityList;
	private String role;
	private String gender;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

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

	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
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

	public List getSecurityList() {
		return securityList;
	}

	public void setSecurityList(List securityList) {
		this.securityList = securityList;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String execute() throws Exception {
		UserDTO userDTO = new UserDTO();
		if ("SignUp".equals(operation)) {
			userDTO.setFirstName(firstName);
			userDTO.setLastName(lastName);
			userDTO.setLogin(login);
			userDTO.setPassword(password);
			byte[] im = FileUtils.readFileToByteArray(this.image);
			userDTO.setImage(im);
			userDTO.setDateOfBirth(dateOfBirth);
			userDTO.setCount(0);
			userDTO.setRole(role);
			userDTO.setAnswer(answer);
			userDTO.setSecurityQuestion(securityQuestion);
			userDTO.setGender(gender);
			userDTO.setLockSummery(new Date());
			userDTO.setLastAccessTime(null);
			userDTO.setMobileNumber(mobileNumber);

			long id = UserBussinessDelegate.add(userDTO);
			if (id > 0) {
				return "add";
			} else {
				addActionError("please Choose Another loginId");
				return "NotAdded";
			}

		} else {

		}

		return SUCCESS;
	}

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return INPUT;
	}

	@Override
	public void prepare() throws Exception {
		securityList=UserBussinessDelegate.getSecurityQuestion();
	
	}
	
	

}
