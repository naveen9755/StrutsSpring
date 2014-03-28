package com.testMail;

import com.emailService.MailServiceImpl;
import com.emailService.MailServiceInt;

public class SimpleTest {
	public static void main(String args[]) throws Exception {

		// Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
		MailServiceInt mailService = new MailServiceImpl();
		String toEmail = "chandrabhan.ips11@gmail.com";

		mailService.sendMail(toEmail, "hello", "hi Friend How are you???");
		System.out.println("Sucessfully sent mail to all Users");
	}
}
