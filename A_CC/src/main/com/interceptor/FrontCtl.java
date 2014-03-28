package com.interceptor;

import java.util.Map;

import com.dto.UserDTO;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class FrontCtl extends AbstractInterceptor {

	@Override
	public void destroy() {
		System.out.println("In FrontCtl destroy");
	}

	@Override
	public void init() {
		System.out.println("In FrontCtl init");
	}

	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {

		ActionSupport actionSupport = (ActionSupport) actionInvocation
				.getAction();

		Map session = actionInvocation.getInvocationContext().getSession();
		System.out
				.println(actionInvocation.getInvocationContext().getSession());
		UserDTO userDTO = (UserDTO) session.get("user");
		if (userDTO == null) {
			System.out.println("In logout if.........");
			actionSupport.addActionError("Oop's your session is Expired");
			return "logout";
		}
		return actionInvocation.invoke();
	}
}
