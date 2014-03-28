package com.jtest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.dto.UserDTO;
import com.locator.ServiceLocator;
import com.service.UserServiceInt;

public class UserTestCase {

	private UserDTO userDTO = new UserDTO();
	private static UserServiceInt userService = ServiceLocator.getInstance()
			.getUserService();

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
		System.out.println("In setUp ");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("in Tear Down");
	}

	@Test
	public void testAdd() throws Exception {

		userDTO.setId(3);
		userDTO.setFirstName("Vinay");
		userDTO.setLastName("Singh");
		userDTO.setLogin("chandrabhan.ips11@gmail.com");
		userDTO.setPassword("aa");
		Date date = new Date("2013/09/12 12:08:45");

		userDTO.setDateOfBirth(date);
		userDTO.setLastAccessTime(new Date());
		userDTO.setLockSummery(new Date());

		userService.add(userDTO);
		userDTO = userService.get(userDTO.getId());
		assertNotNull("Error: Record is not inserted in database...", userDTO);
		System.out
				.println("Success:Record is Successfully inserted in database..");
	}

	@Ignore
	public void testGet() throws Exception {
		userDTO = userService.get(1);
		assertNotNull("Error:Record is not exist in DataBase", userDTO);
		System.out.println("Success: User Name is:" + userDTO.getFirstName());
	}

	@Ignore
	public void testUpdate() throws Exception {
		userDTO = userService.get(1);
		userDTO.setLogin("kk");
		userDTO.setDateOfBirth(new Date());
		userService.update(userDTO);
		UserDTO userDTO2 = new UserDTO();

		userDTO2 = userService.get(userDTO.getId());

		assertEquals("Error: record is not Updated...", userDTO.getLogin(),
				userDTO2.getLogin());
		System.out.println("Success:Record is Updated.....");
	}
	
	@Ignore
	public void testAuthonticate() throws Exception {
		userDTO = (UserDTO) userService.authonticate("jay", "baba");
		assertNotNull("Error: User is not Authonticated...", userDTO);
		System.out
				.println("Success: User is Authonticated ande user Name is...."
						+ userDTO.getFirstName());
	}

	@Ignore
	public void testSearch() throws Exception {
		userDTO.setFirstName("c");
		List list = userService.search(userDTO);
		Iterator iterator = list.iterator();
		assertTrue("Error: List is Null", list.size() > 0);
		System.out.println("Success: ");
		while (iterator.hasNext()) {
			userDTO = (UserDTO) iterator.next();
			System.out.println(userDTO.getFirstName());
		}
	}

	@Ignore
	public void testChangePassword() throws Exception {
		Boolean str = userService.changePassword(1, "aa", "bbabj");
		System.out.println("Success:" + str);
	}

	@Ignore
	public void testLockUser() throws Exception {
		userDTO.setId(9);
		boolean b = userService.lockUser(userDTO);
		assertTrue("Error:User Not Locked", b);
	}

}
