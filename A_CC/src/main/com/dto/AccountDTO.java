package com.dto;

import java.util.Date;

public class AccountDTO {
	private Integer id;
	private Double balance;
	private String type;
	private Date openDate;
	private Date lastAccessTime;
	private Date lockSummery;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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
	
	

}