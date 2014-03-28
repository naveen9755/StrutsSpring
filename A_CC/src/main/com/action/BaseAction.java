package com.action;

import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public abstract class BaseAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware, SessionAware,
		ApplicationAware, Preparable {

	protected Integer id;
	protected Logger logger;
	protected String operation;
	protected ServletRequest request;
	protected ServletResponse response;
	protected Map session;
	protected Map application;

	@Override
	public abstract String execute() throws Exception;

	@Override
	public abstract String input() throws Exception;

	@Override
	public final void validate() {
		// TODO Auto-generated method stub
		super.validate();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void prepare() throws Exception {
		// TODO Auto-generated method stub

	}

	public void setSession(Map<String, Object> session) {
		this.session = session;

	}

	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub

	}

	public void setApplication(Map<String, Object> application) {
		this.application = application;

	}
}
