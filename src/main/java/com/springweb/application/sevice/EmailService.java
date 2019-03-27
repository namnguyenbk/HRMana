package com.springweb.application.sevice;

import java.util.Date;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Value("")
	private String username;
	@Value("")
	private String password;

	@Autowired
	private JavaMailSender sender;

	public int sendEmailTo(String emailAddress,String msgContent) {
		try{
			MimeMessage message = sender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message,true);
			helper.setTo(emailAddress);
			helper.setText(msgContent);
			helper.setSubject("Verify account HRMana" );
			sender.send(message);
		}catch (Exception e){
			e.printStackTrace();
			return 0;
		}
//		try {
//		Properties props=new Properties();
//		props.put("mail.smtp.auth", "true");
//		props.put("mail.smtp.starttls.enable", "true");
//		props.put("mail.smtp.host", "smtp.gmail.com");
//		props.put("mail.smtp.port", "587");
//		Authenticator auth=new Authenticator() {
//		protected PasswordAuthentication getPasswordAuthentication() {
//		return new PasswordAuthentication(username, password);
//		}
//
//		};
//		Session session=Session.getInstance(props,auth);
//		Message msg=new MimeMessage(session);
//		Address address=new InternetAddress(username,false);
//
//		msg.setFrom(address);
//
//		msg.setRecipient(Message.RecipientType.TO,(Address)InternetAddress.parse(emailAddress)[0]);
//		msg.setSubject("Redirect to: ");
//		msg.setContent(msgContent, "text/html");
//		msg.setSentDate(new Date());
//		Transport.send(msg);
//		} catch (Exception e) {
//		e.printStackTrace();
//		return 0;
//		}
		return 1;
		}

	@Bean
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
		mailSender.setUsername("20166477@student.hust.edu.vn");
		mailSender.setPassword("01686137754");

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "true");
		return mailSender;
	}
}


