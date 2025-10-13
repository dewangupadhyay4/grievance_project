package com.grievance.resolve.service;

import java.util.Properties;

import org.springframework.stereotype.Service;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;


@Service
public class EmailService {

	public void sendOtpEmail(String to, String otp) {
		String from="dewangbackup1@gmail.com";
		String host="smtp.gmail.com";
		Properties props=new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		
		Session session=Session.getInstance(props, new Authenticator() {
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication("dewangbackup1@gmail.com", "zsjqpnhxzwpxrujm");
		}
		});
		
		try {
			MimeMessage message=new MimeMessage(session);
			message.setFrom(from);
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("Your OTP Code from Grievance Portal");
			message.setText("Your OTP is: "+otp);
			Transport.send(message);
		}catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
}
