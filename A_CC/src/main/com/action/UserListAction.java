package com.action;

import com.delegate.UserBussinessDelegate;

public class UserListAction extends BaseListAction {

	@Override
	public String execute() throws Exception {
		if ("Delete".equals(operation)) {
			for (int i = 0; i < ids.length; i++) {
				UserBussinessDelegate.delete(ids[i]);
			}
			input();
		}

		return SUCCESS;
	}

	@Override
	public String input() throws Exception {
		dtoList = UserBussinessDelegate.getList();
		return SUCCESS;

	}

}
