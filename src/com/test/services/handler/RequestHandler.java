package com.test.services.handler;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import com.test.constants.Constants;



@Path("MailServices")
public class RequestHandler {

	@POST
	@Consumes(Constants.REST_REQUEST_TYPE)
	@Path("send_mail")
	public static String sendMail(@FormParam("subject") String subject,@FormParam("body") String body) 
	{
		String response;
		String tocc1="bikash@thelattice.in,bikash@thelattice.in";
		String to1 = "bikashjana2008@gmail.com";
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtpout.secureserver.net");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		props.put("mail.debug", "true");
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("notification@camtechmgh.org", "N0t1f1c@tion");
			}
		});
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress("support@camtechmgh.org"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to1));
			message.addRecipient(Message.RecipientType.CC, new InternetAddress(tocc1));
			message.setSubject(subject);
			message.setContent(body, "text/html");
			Transport.send(message);
			System.out.println("Message sent");
			response = "message sent";
		} catch (MessagingException e) {
			System.out.println("Mail not sent. Error: " + e.getMessage());
			response = "Mail not sent. Error: " + e.getMessage();
		}
		return response;
	}

	
	//test service add 
}






