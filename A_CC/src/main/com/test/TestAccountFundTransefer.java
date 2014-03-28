package com.test;

import org.apache.log4j.Logger;

import com.locator.ServiceLocator;
import com.service.AccountServiceInt;

public class TestAccountFundTransefer {
	static Logger logger = Logger.getLogger(TestAccountFundTransefer.class);

	public static void main(String[] args) throws Exception {
		//XmlBeanFactory factory = new XmlBeanFactory(new ClassPathResource(
				//"applicationContext.xml"));
		//AccountServiceInt accountService = (AccountServiceInt) factory
				//.getBean("BaseService");
		 AccountServiceInt accountService = ServiceLocator.getInstance()
		 .getAccountService();

		double str = accountService.fundTransfer(1002, 1001, 500d);
		logger.debug(str);
	}
}