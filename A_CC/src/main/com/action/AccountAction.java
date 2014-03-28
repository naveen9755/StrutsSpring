package com.action;

import java.util.Date;

import com.dto.AccountDTO;

public class AccountAction extends BaseAction {

	private Double balance;
	private String type;
	private Date openDate;
	private Date lastAccessTime;
	private Date lockSummery;

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	public Date getLastAccessTime() {
		return lastAccessTime;
	}

	public void setLastAccessTime(Date lastAccessTime) {
		this.lastAccessTime = lastAccessTime;
	}

	public Date getLockSummery() {
		return lockSummery;
	}

	public void setLockSummery(Date lockSummery) {
		this.lockSummery = lockSummery;
	}

	@Override
	public String execute() throws Exception {
		AccountDTO accountDTO = new AccountDTO();
		AccountDTO dbDTO = new AccountDTO();
		if ("Add".equalsIgnoreCase(operation)) {
			//dbDTO.setId(id);

		} else {
			addActionError("Account No is All-ready exist. Please choose diffrent one.");
		}
		return SUCCESS;
	}

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
