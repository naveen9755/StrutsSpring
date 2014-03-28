package com.jtest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import junit.framework.Test;
import junit.framework.TestSuite;

@RunWith(Suite.class)
@SuiteClasses( { AccountTestCase.class, UserTestCase.class })
	
public class TestsSuite {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for com.jtest");
		// $JUnit-BEGIN$
		//suite.addTestSuite(TestAccountService.class);
		//suite.addTestSuite(TestUserService.class);
		// $JUnit-END$
		return suite;
	}

}
