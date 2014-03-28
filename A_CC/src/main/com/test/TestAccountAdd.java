package com.test;

import java.util.Date;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import com.dto.AccountDTO;
import com.locator.ServiceLocator;
import com.service.AccountServiceInt;

public class TestAccountAdd {
	public static void main(String[] args) throws Exception {
		/**XmlBeanFactory factory = new XmlBeanFactory(new ClassPathResource(
				"applicationContext.xml"));
		AccountServiceInt accountService = (AccountServiceInt) factory
				.getBean("BaseService");
				*/
		AccountServiceInt accountService=ServiceLocator.getInstance().getAccountService();
		
		AccountDTO accountDTO = new AccountDTO();

		accountDTO.setId(1005);
		accountDTO.setBalance(234589d);
		accountDTO.setOpenDate(new Date());
		accountDTO.setType("Saving");
		accountDTO.setLastAccessTime(new Date());
		accountDTO.setLockSummery(new Date());

		accountDTO=accountService.openAccount(accountDTO);
		System.out.println(accountDTO.getBalance());

	}
}