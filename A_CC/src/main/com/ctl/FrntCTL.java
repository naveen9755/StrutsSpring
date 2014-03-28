package com.ctl;

import java.util.Map;

import org.hibernate.Session;

import com.dto.UserDTO;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class FrntCTL extends AbstractInterceptor {

	@Override
	public void destroy() {
		System.out.println("in FrntCTL destroy()");
	}

	@Override
	public void init() {
		System.out.println("In FrntCTl init()");
	}

	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		Map session = actionInvocation.getInvocationContext().getSession();
		ActionSupport actionSupport = (ActionSupport) actionInvocation
				.getAction();
		if (session.get("user") != null) {
			actionSupport.addActionError("Your session has expired");
		}
		return actionInvocation.invoke();
	}

}
