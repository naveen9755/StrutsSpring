package com.action;

import com.delegate.UserBussinessDelegate;
public class ChangePasswordAction extends BaseAction {
	private Integer id;
	private String oldPassword;
	private String newPassword;
	private String confrmPassword;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfrmPassword() {
		return confrmPassword;
	}

	public void setConfrmPassword(String confrmPassword) {
		this.confrmPassword = confrmPassword;
	}

	@Override
	public String execute() throws Exception {
		if ("ChangePassword".equals(operation)) {
			boolean flag = UserBussinessDelegate.changePassword(id,
					oldPassword, confrmPassword);
			if (flag==true) {
				addActionMessage("Password is change successfully");
				return "passwordChange";
			} else {
				addActionError("Please Enter charect password");
				return "passwordNotChange";
			}
			

		}
		return SUCCESS;
	}

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return INPUT;
	}

}
