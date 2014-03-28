package com.emailService;

import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailServiceImpl implements MailServiceInt {
	static ResourceBundle bundle=ResourceBundle.getBundle("com.bundle.email");
	static ResourceBundle msgBundle=ResourceBundle.getBundle("com.bundle.emailMessage");

	private static final String SMTP_HOST_NAME = bundle.getString("smtp.server");
	private static final String SMTP_PORT = bundle.getString("smtp.port");
	private static final String emailMsgTxt =msgBundle.getString("emailMessage");
	private static final String emailSubjectTxt = msgBundle.getString("emailSubject");
	private static final String emailFromAddress = bundle.getString("email.login");
	private static final String emailPassword=bundle.getString("email.pwd");
	private static final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

	public void sendMail(String toEmail, String subject,
			String message) throws MessagingException {
		boolean debug = true;
		Properties props = new Properties();
		props.put("mail.smtp.host", SMTP_HOST_NAME);
		props.put("mail.smtp.auth", "true");
		props.put("mail.debug", "true");
		props.put("mail.smtp.port", SMTP_PORT);
		props.put("mail.smtp.socketFactory.port", SMTP_PORT);
		props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
		props.put("mail.smtp.socketFactory.fallback", "false");

		Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(
								emailFromAddress,emailPassword);
					}
				});

		session.setDebug(debug);

		Message msg = new MimeMessage(session);
		InternetAddress addressFrom = new InternetAddress(emailFromAddress);
		msg.setFrom(addressFrom);

		InternetAddress addressTo = new InternetAddress(toEmail);

		msg.setRecipient(Message.RecipientType.TO, addressTo);

		// Setting the Subject and Content Type
		msg.setSubject(subject);
		msg.setContent(message, "text/plain");
		Transport.send(msg);
	}
}
