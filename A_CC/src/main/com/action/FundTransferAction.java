package com.action;

import java.util.Date;

import com.delegate.AccountBussinessDelegate;
import com.dto.AccountDTO;

public class FundTransferAction extends BaseAction {

	private Integer depositerId;
	private Double amount;
	private Integer reciverId; 
	
	

	public Integer getDepositerId() {
		return depositerId;
	}

	public void setDepositerId(Integer depositerId) {
		this.depositerId = depositerId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Integer getReciverId() {
		return reciverId;
	}

	public void setReciverId(Integer reciverId) {
		this.reciverId = reciverId;
	}

	@Override
	public String execute() throws Exception {
		if ("FundTransfer".equalsIgnoreCase(operation)) {
			double balance=AccountBussinessDelegate.fundTransfer(depositerId, reciverId, amount);
			addActionMessage("Fund is transferd Successfully and now your A/c. Balance is :"+balance);
			return "fundTransfer";

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
