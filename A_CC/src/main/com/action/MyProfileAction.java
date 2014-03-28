package com.action;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.delegate.UserBussinessDelegate;
import com.dto.UserDTO;

public class MyProfileAction extends BaseAction {
	private String firstName;
	private String lastName;
	private String login;
	private String password;
	private String newPassword;
	private String confirmPassword;
	private File image;
	private String mobileNumber;
	private Date dateOfBirth;
	private String securityQuestion;
	private String answer;
	private List securityList;
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

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String execute() throws Exception {
		UserDTO userDTO=new UserDTO();
		if ("Save".equals(operation)) {
			userDTO.setId(id);
			userDTO.setFirstName(firstName);
			userDTO.setLastName(lastName);
			userDTO.setPassword(confirmPassword);
			userDTO.setAnswer(answer);
			userDTO.setDateOfBirth(dateOfBirth);
			userDTO.setGender(gender);
			byte[] file=FileUtils.readFileToByteArray(image);
			userDTO.setImage(file);
			userDTO.setSecurityQuestion(securityQuestion);
			userDTO.setAnswer(answer);
			userDTO.setMobileNumber(mobileNumber);
			UserBussinessDelegate.update(userDTO);
			addActionMessage("Profile is successfully Edited");
			return "Edited";
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
		super.prepare();
	}
	

}
