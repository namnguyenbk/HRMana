package com.springweb.application.sevice;

import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Value("${email.username}")
	private String username;
	@Value("${email.password}")
	private String password;
	
	public int sendEmailTo(String emailAddress,String msgContent) {

		try {
		Properties props=new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		Authenticator auth=new Authenticator() {
		protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(username, password);
		}

		};
		Session session=Session.getInstance(props,auth);
		Message msg=new MimeMessage(session);
		Address address=new InternetAddress(username,false);

		msg.setFrom(address);

		msg.setRecipient(Message.RecipientType.TO,(Address)InternetAddress.parse(emailAddress)[0]);
		msg.setSubject("Redirect to: ");
		msg.setContent(msgContent, "text/html");
		msg.setSentDate(new Date());
		Transport.send(msg);
		} catch (Exception e) {
		e.printStackTrace();
		return 0;
		}
		return 1;
		}
}
