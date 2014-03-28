package com.delegate;

import javax.mail.MessagingException;

import com.emailService.MailServiceInt;
import com.locator.ServiceLocator;

public class EmailBussinessDelegate {
	private EmailBussinessDelegate() {
	}

	private static MailServiceInt mailService = (MailServiceInt) ServiceLocator
			.getInstance().getMailService();

	public static void sendMail(String toEmail, String subject, String message)
			throws MessagingException {
		mailService.sendMail(toEmail, subject, message);
	}

}
