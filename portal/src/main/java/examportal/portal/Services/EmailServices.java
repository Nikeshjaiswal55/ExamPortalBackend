package examportal.portal.Services;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
// import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
// import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServices {

	public boolean sendEmail(String from,String subject, String message, String to) {
//		rest of the code.....

		boolean f = false;


//		variable for gmail
		String host = "smtp.gmail.com";

//		get the system properties

		Properties properties = System.getProperties();
		System.out.println("PROPERTIES : " + properties);
//		setting important information to properties object

//		host set
		properties.put("mail.smtp.host",host);
		properties.put("mail.smtp.port","465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

//		STEP - 1 : to get the session object

		Session session = Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("devendrap.bca2021@ssism.org","dev@2901");
			}

		});

		session.setDebug(true);

//		STEP 2 : compose the message [text,multi media]

		MimeMessage mimeMessage = new MimeMessage(session);


		try {

//			from email id
			mimeMessage.setFrom(from);

//			add recipient to message

			mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

//			adding subject to message

			mimeMessage.setSubject(subject);

//			adding text to message

			mimeMessage.setText(message);

//			send
//			STEP 3: send the message using transport class 
			Transport.send(mimeMessage);

			System.out.println("send successfully....");

			f = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return f;
	}

	
	
// 	public boolean sendEmail(String subject,String message,String to,String cc,String bcc) throws MessagingException {
		

// 		//this is responsible to send email..

// 	boolean f= false;
	
// 	String from ="devendrap.bca2021@ssism.org"; 

//    message="<!DOCTYPE html>" 
// 	+"<html lang='en'>" 
	
// 	+ "<head>"
// 		+ "<meta charset='UTF-8'>"
// 		+"<meta http-equiv='X-UA-Compatible' content='IE=edge'>"
// 		+"<meta name='viewport' content='width=device-width, initial-scale=1.0'>"
//         +"<title>Reset Password</title>"

//     + "<style>@import url('https://fonts.googleapis.com/css2?family=Raleway:wght@100;200;300;400;500;600;700;800;900&display=swap'); * {font-family: Raleway;}.wrapper {display: flex;justify-content: center;align-content: center;}.containerr {margin-top: 3rem;box-shadow: 0px 0px 10px #00000029;width: 526px;height: fit-content;}.logo {display: flex;align-items: center;padding-top: .5rem;padding-left: 1rem;}.logo img {width: 38px;}.logo span {font-size: 14px;padding-left: .5rem;font-weight: 500;}.line {width: 90%;padding-left: 1.5rem;}.line hr {border: 1px solid #707070;opacity: 50%;}.heading1 {padding-left: 1.5rem;}.heading1 h3 {font-size: 22px;font-weight: bold;}.paragraph {display: flex;justify-content: center;align-items: center;padding-left: 1.5rem;padding-right: 1.5rem;}.paragraph p {font-size: 0.75rem;font-weight: 500;}.btn {display: flex;justify-content: start;margin-top: .5rem;padding-left: 1.5rem;}.btn button {background-color: #F2541E;width: 182px;height: 28px;color: white;border: none;border-radius: 2px;font-weight: bold;}.paragraph2 p {font-size: 0.625rem;padding-left: 1.5rem;opacity: 70%;font-weight: 500;}.paragraph3 {margin-bottom: 1.2rem;}.paragraph3 p {font-size: 0.625rem;margin-top: 2rem;padding-left: 1.5rem;padding-right: 1.5rem;font-weight: 500;}.paragraph4 {padding-left: 1.5rem;padding-right: 1.5rem;padding-bottom: 30px;}.paragraph4 p {font-size: 0.75rem;font-weight: 500;}.paragraph4 p span a {color: #16459A;font-weight: 500;}.paragraph4 a {color: #16459A;text-decoration: none;font-weight: 600;}</style>"
// +"</head>"

// +"<body><div class='wrapper'><div class='containerr'><div class='logo'><img src='https://ibb.co/2qXz5T2' alt='ssismLogo' /><span>Sant singaji institute of science & <br /> management sandalpur </span></div><div class='line'><hr></hr></div><div class='heading1'>  <h3>Hey devid</h3></div><div class='paragraph'> <p> We’ve received a request to reset your account’s password . To continue, click on the Button below to reset your password.</p></div><div class='btn'><button type='button'>Reset password</button></div><div class='paragraph2'><p>This password reset link will expire in 60 minutes</p></div><div class='paragraph3'><p>Did’t request for a password reset ?Please reach out to our customer support team.</p></div><div class='line'><hr></hr></div><div class='paragraph4'><p>If you’re having trouble clicking the button,<span><a href='#'>please contact our support team.</a></span></p><a href='#'>Contact@ssism.org</a></div></div></div></body></html>";
			
// 			//Variable for Gmail

// 			String host="smtp.gmail.com";
			
// 			//get the system properties
// 			Properties properties = System.getProperties();
// 			System.out.println("PROPERTIES "+properties);
			
// 			//setting important information to properties object
			
// 			//host set
// 			properties.put("mail.smtp.host", host);
// 			properties.put("mail.smtp.port","465");
// 			properties.put("mail.smtp.ssl.enable","true");
// 			properties.put("mail.smtp.auth","true");
			
// 			//Step 1: to get the session object..
// 			Session session=Session.getInstance(properties, new Authenticator() {
// 				@Override
// 				protected PasswordAuthentication getPasswordAuthentication() {				
// 					return new PasswordAuthentication("devendrap.bca2021@ssism.org","dev@2901");
// 				}
				
				
				
// 			});
			
// 			session.setDebug(true);
			
// 			//Step 2 : compose the message [text,multi media]
// 			MimeMessage m = new MimeMessage(session);
// 			// MimeMessageHelper helper = new MimeMessageHelper(m, true); 

// 			try {
			
// 			//from email
// 			m.setFrom(from);
			
// 			//adding subject to message
// 			m.setSubject(subject);
			
// 			//adding text to message
// 			m.setText(message);
		
// 			//adding recipiesnt to message
// 			m.setReplyTo(InternetAddress.parse(to));

// 			//adding cc
// 			m.setRecipients(Message.RecipientType.CC, InternetAddress.parse(cc));
			
// 			//adding bcc
// 			m.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(bcc));

// 			//attachment file
// 			// String[] files = file;
// 			// 	for(String singleString: files) {
// 			// 		String ss=singleString.split(",")[1];
// 			// 		byte[] data = DatatypeConverter.parseBase64Binary(ss);
// 			// 		System.out.println(data);
// 			// 		InputStreamSource add= new ByteArrayResource(data);
				
// 			// 		helper.addAttachment("attachment", add,
// 			// 		singleString.split(",")[0].replaceAll("data:", "").replaceAll(";base64", ""));
					
// 				// }
				
			
	
			
			
			
// 			//send  
// 			//Step 3 : send the message using Transport class
// 			Transport.send(m);
			
// 			System.out.println("Sent success...................");
// 			f=true;
			
// 			}catch (Exception e) {
// 				e.printStackTrace();
// 			}
				
// 			return f;
// 		}

}
