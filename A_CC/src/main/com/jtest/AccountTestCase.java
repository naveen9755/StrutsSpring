package com.jtest;

import static org.junit.Assert.assertNotNull;

import java.util.Date;

import javax.security.auth.login.FailedLoginException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.dto.AccountDTO;
import com.locator.ServiceLocator;
import com.service.AccountServiceInt;

public class AccountTestCase {
	private static AccountServiceInt accountService = ServiceLocator
			.getInstance().getAccountService();
	private AccountDTO accountDTO = new AccountDTO();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		System.out.println("in setUpBeforeClass");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("in tearDownAfterClass");
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("In SetUp");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("In Tear Down");
	}

	@Test
	public void testDeposite() throws Exception {
	
		double str = accountService.deposite(1001, 2500d);
		System.out.println(str);
		
	}

	@Test
	public void testFundTransfer() throws Exception {
		double balance = accountService.fundTransfer(1001, 1002, 2500d);
		System.out.println(balance);
	}

	@Test
	public void testLockAccount() {
		// fail("Not yet implemented");
	}

	@Test
	public void testOpenAccount() throws Exception {
		accountDTO.setId(1002);
		accountDTO.setBalance(5000d);
		accountDTO.setOpenDate(new Date());
		accountDTO.setType("Current");
		accountDTO.setLastAccessTime(new Date());
		accountDTO.setLockSummery(new Date());
		AccountDTO str = accountService.openAccount(accountDTO);
		accountDTO = accountService.get(accountDTO.getId());
		assertNotNull("Error: DataBase Error... so Account Does not Open.....",
				accountDTO);
		
		System.out.println("Success: Account is Successfully Open...");
	}

	@Test
	public void testWithDrawal() throws Exception {
		double str = accountService.withDrawal(1001, 2000d);
		System.out.println(str);
	}

	@Test
	public void testBalance() throws Exception {
		Double balance = accountService.balance(1001);
		System.out.println("Success: your A/c. Balance is:--" + balance);
	}

	@Test
	public void testGet() throws Exception {
		accountDTO = accountService.get(1001);
		assertNotNull("Error: Enter valid A/c. No....", accountDTO);
		System.out.println("Success: A/c. is exist....." + accountDTO.getType()
				+ "" + accountDTO.getId());
		
	}
}
