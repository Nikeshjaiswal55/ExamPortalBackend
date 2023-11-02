// package examportal.portal.Services;

// import java.util.Properties;

// import javax.mail.Authenticator;
// import javax.mail.Message;
// // import javax.mail.MessagingException;

// import javax.mail.PasswordAuthentication;
// import javax.mail.Session;
// import javax.mail.Transport;
// import javax.mail.internet.InternetAddress;
// import javax.mail.internet.MimeMessage;
// // import org.springframework.mail.javamail.MimeMessageHelper;
// import org.springframework.stereotype.Service;

// @Service
// public class EmailServices {

// 	public boolean sendEmail(String from,String subject, String message, String to) {
// //		rest of the code.....

// 		boolean f = false;
// //		variable for gmail
// 		String host = "smtp.gmail.com";

// //		get the system properties

// 		Properties properties = System.getProperties();
// 		System.out.println("PROPERTIES : " + properties);
// //		setting important information to properties object

// //		host set
// 		properties.put("mail.smtp.host",host);
// 		properties.put("mail.smtp.port","465");
// 		properties.put("mail.smtp.ssl.enable", "true");
// 		properties.put("mail.smtp.auth", "true");

// //		STEP - 1 : to get the session object

// 		Session session = Session.getInstance(properties, new Authenticator() {

// 			@Override
// 			protected PasswordAuthentication getPasswordAuthentication() {
// 				return new PasswordAuthentication("krishnas.bca2022@ssism.org","");
// 			}

// 		});

// 		session.setDebug(true);

// //		STEP 2 : compose the message [text,multi media]

// 		MimeMessage mimeMessage = new MimeMessage(session);


// 		try {

// //			from email id
// 			mimeMessage.setFrom(from);

// //			add recipient to message

// 			mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

// //			adding subject to message

// 			mimeMessage.setSubject(subject);

// //			adding text to message

// 			mimeMessage.setText(message);

// //			send
// //			STEP 3: send the message using transport class 
// 			Transport.send(mimeMessage);

// 			System.out.println("send successfully....");

// 			f = true;

// 		} catch (Exception e) {
// 			e.printStackTrace();
// 		}

// 		return f;
// 	}


	

// }
