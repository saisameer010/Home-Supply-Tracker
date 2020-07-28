package com.SupplyTracker.Purchase;

import java.util.*;
import com.SupplyTracker.Pojo.Product;
import javax.mail.Session;
import javax.mail.Transport;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {
	public void SendMail(HashMap<String, Product> hm) {

		// Getting system properties
		Properties props = System.getProperties();

		// Setting up mail server
		final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
		// Get a Properties object
		props.setProperty("mail.smtp.host", "smtp.gmail.com");
		props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.port", "465");
		props.setProperty("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.auth", "true");
		props.put("mail.debug", "true");
		props.put("mail.store.protocol", "pop3");
		props.put("mail.transport.protocol", "smtp");
		final String username = "InsertEmaiId";//
		final String password = "InsertPassword";
		
		
		// creating session object to get properties
		
		try {
			// MimeMessage object.
			Session session = Session.getDefaultInstance(props, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});

// -- Create a new message --
			Message message = new MimeMessage(session);

// -- Set the FROM and TO fields --
			message.setFrom(new InternetAddress("send from EmailId"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("send to emailId", false));
			message.setSubject("Order Details");
			// set body of the email.
			String html = "\"<h1>This The Order in table format</h1>\"<br/>" + "<table align=\"centre\" border=border>"
					+ "<tr><th>Sno</th>" + "<th>Type</th>" + "<th>Name</th>" + "<th>Price</th>" + "<th>Quantity</th>"
					+ "<th>Description</th>" + "</tr>";
			int i=0;
			for (Map.Entry mapElement : hm.entrySet()) {
				String key = (String) mapElement.getKey();
				i++;
				Product value = ((Product) mapElement.getValue());
				html += "<tr><td>"+i+"</td><td>" + value.getType() + "</td>" + "<td>" + value.getName() + "</td>" + "<td>"
						+ value.getPrice() + "</td>" + "<td>" + value.getQuantity() + "</td>" + "<td>" + value.getDesc()
						+ "</td>" + "</tr>";
			}
			message.setContent(html, "text/html");

			// Send email.
			Transport.send(message);
			System.out.println("Mail successfully sent");
		} catch (MessagingException mex) {
			System.out.println("Catch Mail");
			mex.printStackTrace();
		}
	}
}
