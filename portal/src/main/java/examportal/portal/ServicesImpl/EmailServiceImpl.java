package examportal.portal.ServicesImpl;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl {

    Logger log = org.slf4j.LoggerFactory.getLogger("EmailServiceImpl.class");

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    public String sendSimpleMail(String to, String msg, String sub) {
        log.info("EmailSeviceImpl , sendSimpleMail Method Start");
        // Try block to check for exceptions
        try {
            // Creating a simple mail message
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            // Setting up necessary details
            mailMessage.setFrom(sender);
            mailMessage.setTo(to);
            mailMessage.setText(msg);
            mailMessage.setSubject(sub);

            // Sending the mail
            javaMailSender.send(mailMessage);
            log.info("EmailSeviceImpl , sendSimpleMail Method Ends");
            return "Mail Sent Successfully...";
        }

        // Catch block to handle the exceptions
        catch (Exception e) {
            return "Error while Sending Mail";
        }

    }
// this usse 

    public String sendFormateMail(String to, String msg, String sub, String role) {
        log.info("EmailServiceImp , sendFormateMail Method Start");
        
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
            helper.setTo(to);
            helper.setSubject(sub);
            helper.setText(msg, true);
            javaMailSender.send(message);
            log.info("EmailServiceImp , sendFormateMail Method End");
            return "Mail Succesfully to" + to;
        } catch (Exception e) {
            System.out.println(e);
            return "Mail Not Done ";
        }
    }

    public String sendMailForInvitingStudent(String to, String msg, String sub){
          String msgbody = "<!DOCTYPE html>\n" + //
                    "<html lang=\"en\">\n" + //
                    "<head>\n" + //
                    "    <meta charset=\"UTF-8\">\n" + //
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" + //
                    "    <title>Login1</title>\n" + //
                    "</head>\n" + //
                    "<body style=\"background-color: rgb(236, 239, 242); font-family: Arial, sans-serif; margin: 0; padding: 0;\">\n"
                    + //
                    "\n" + //
                    "    <img src=\"https://res.cloudinary.com/dvln9maxh/image/upload/v1702210266/x4nnb4vpsv4bcw8mkzlf.jpg\"\n"
                    + //
                    "        style=\"opacity: 1; margin: 10px auto; display: block; width: 200px; height: auto; max-width: 100%; margin-top: 70px ; \"\n"
                    + //
                    "        onmouseover=\"this.style.opacity = '1.0';\" onmouseout=\"this.style.opacity = '1';\">\n" + //
                    "\n" + //
                    "    <div style=\"background-color: white; margin: 10px auto; width: 80%; max-width: 600px; height: auto; margin-top: 40px; padding: 20px; font-size: 1.2em; border-radius: 5px; \">\n"
                    + //
                    "\n" + //
                    "        <h1 style=\"font-size: 2em; text-align: center;\">Welcome To ExamEasy</h1>\n" + //
                    "\n" + //
                    "        <p>Get ready for a smoother exam experience</p>\n" + //
                    "        <p>Please use the provided passoword to login at ExamEasy</p>\n" + //
                    "        <p>Password: <b>" + msg + "</b></p>\n" + //
                    "        <button type=\"button\" class=\"login-btn\" style=\"background-color: black; color: white; border-radius: 10px; padding: 10px 30px; display: block; margin: 20px auto;\">Login</button>\n"
                    + //
                    "\n" + //
                    "        <h4>Need a hand? Reach us at <a href=\"mailto:exameasy.official@gmail.com\">exameasy.official@gmail.com</a></h4>\n"
                    + //
                    "\n" + //
                    "        <p>Cheers,<br>Team ExamEasy</p>\n" + //
                    "    </div>\n" + //
                    "\n" + //
                    "    <style>\n" + //
                    "        @media only screen and (max-width: 600px) {\n" + //
                    "            img {\n" + //
                    "                width: 100%;\n" + //
                    "                margin-top: 20px;\n" + //
                    "            }\n" + //
                    "\n" + //
                    "            div {\n" + //
                    "                width: 90%;\n" + //
                    "                font-size: 1em;\n" + //
                    "            }\n" + //
                    "\n" + //
                    "            h1 {\n" + //
                    "                font-size: 1.5em;\n" + //
                    "            }\n" + //
                    "        }\n" + //
                    "    </style>\n" + //
                    "</body>\n" + //
                    "</html>\n" + //
                    "";

        return  sendFormateMail(to, msgbody, sub,null); 
    }



public String sendMailForSuccessufullyCreatingOG(String to, String msg, String sub){

       String msgbody = "\n" + //
                    "<!DOCTYPE html>\n" + //
                    "<html lang=\"en\">\n" + //
                    "\n" + //
                    "<head>\n" + //
                    "    <meta charset=\"UTF-8\">\n" + //
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" + //
                    "    <title>Login1</title>\n" + //
                    "</head>\n" + //
                    "\n" + //
                    "<body style=\"background-color: rgb(236, 239, 242); font-family: Arial, sans-serif; margin: 0; padding: 0;\">\n" + //
                    "\n" + //
                    "    <img src=\"https://res.cloudinary.com/dvln9maxh/image/upload/v1702210266/x4nnb4vpsv4bcw8mkzlf.jpg\"\n" + //
                    "        style=\"opacity: 1; margin: 10px auto; display: block; width: 200px; height: auto; max-width: 100%; margin-top: 40px ; \"\n" + //
                    "        onmouseover=\"this.style.opacity = '1.0';\" onmouseout=\"this.style.opacity = '1';\">\n" + //
                    "\n" + //
                    "    <div\n" + //
                    "        style=\"background-color: white; margin: 10px auto; width: 80%; max-width: 600px; height: auto; margin-top: 40px; padding: 20px; font-size: 1.2em; border-radius: 5px;\">\n" + //
                    "\n" + //
                    "        <h1 style=\"text-align: center;\">Welcome To ExamEasy</h1>\n" + //
                    "\n" + //
                    "        <p>We're here to help you create top-notch assessments. Dive into our tools for customizing assessments,\n" + //
                    "            analyzing performance, and achieving better learning outcomes.</p>\n" + //
                    "\n" + //
                    "        <p><b>Log in with your credentials and let's get started on your assessment journey!</b></p>\n" + //
                    "\n" + //
                    "        <h4>Need a hand? Reach us at <a href=\"exameasy.official@gmail.com\">exameasy.official@gmail.com</a></h4>\n" + //
                    "\n" + //
                    "        <p>Cheers,<br>Team ExamEasy</p>\n" + //
                    "    </div>\n" + //
                    "\n" + //
                    "    <style>\n" + //
                    "        @media only screen and (max-width: 600px) {\n" + //
                    "            img {\n" + //
                    "                width: 100%;\n" + //
                    "                margin-top: 20px;\n" + //
                    "            }\n" + //
                    "\n" + //
                    "            div {\n" + //
                    "                width: 90%;\n" + //
                    "                font-size: 1em;\n" + //
                    "            }\n" + //
                    "\n" + //
                    "            h1 {\n" + //
                    "                font-size: 1.5em;\n" + //
                    "            }\n" + //
                    "        }\n" + //
                    "    </style>\n" + //
                    "</body>\n" + //
                    "\n" + //
                    "</html>\n" + //
                    "";
    
    return sendFormateMail(to, msg, sub, null);
}

}

/*
 *  if (role.equals("Student")) {
            msgbody = "<!DOCTYPE html>\n" + //
                    "<html lang=\"en\">\n" + //
                    "<head>\n" + //
                    "    <meta charset=\"UTF-8\">\n" + //
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" + //
                    "    <title>Login1</title>\n" + //
                    "</head>\n" + //
                    "<body style=\"background-color: rgb(236, 239, 242); font-family: Arial, sans-serif; margin: 0; padding: 0;\">\n"
                    + //
                    "\n" + //
                    "    <img src=\"https://res.cloudinary.com/dvln9maxh/image/upload/v1702210266/x4nnb4vpsv4bcw8mkzlf.jpg\"\n"
                    + //
                    "        style=\"opacity: 1; margin: 10px auto; display: block; width: 200px; height: auto; max-width: 100%; margin-top: 70px ; \"\n"
                    + //
                    "        onmouseover=\"this.style.opacity = '1.0';\" onmouseout=\"this.style.opacity = '1';\">\n" + //
                    "\n" + //
                    "    <div style=\"background-color: white; margin: 10px auto; width: 80%; max-width: 600px; height: auto; margin-top: 40px; padding: 20px; font-size: 1.2em; border-radius: 5px; \">\n"
                    + //
                    "\n" + //
                    "        <h1 style=\"font-size: 2em; text-align: center;\">Welcome To ExamEasy</h1>\n" + //
                    "\n" + //
                    "        <p>Get ready for a smoother exam experience</p>\n" + //
                    "        <p>Please use the provided passoword to login at ExamEasy</p>\n" + //
                    "        <p>Password: <b>" + msg + "</b></p>\n" + //
                    "        <button type=\"button\" class=\"login-btn\" style=\"background-color: black; color: white; border-radius: 10px; padding: 10px 30px; display: block; margin: 20px auto;\">Login</button>\n"
                    + //
                    "\n" + //
                    "        <h4>Need a hand? Reach us at <a href=\"mailto:exameasy.official@gmail.com\">exameasy.official@gmail.com</a></h4>\n"
                    + //
                    "\n" + //
                    "        <p>Cheers,<br>Team ExamEasy</p>\n" + //
                    "    </div>\n" + //
                    "\n" + //
                    "    <style>\n" + //
                    "        @media only screen and (max-width: 600px) {\n" + //
                    "            img {\n" + //
                    "                width: 100%;\n" + //
                    "                margin-top: 20px;\n" + //
                    "            }\n" + //
                    "\n" + //
                    "            div {\n" + //
                    "                width: 90%;\n" + //
                    "                font-size: 1em;\n" + //
                    "            }\n" + //
                    "\n" + //
                    "            h1 {\n" + //
                    "                font-size: 1.5em;\n" + //
                    "            }\n" + //
                    "        }\n" + //
                    "    </style>\n" + //
                    "</body>\n" + //
                    "</html>\n" + //
                    "";

        } else if (role.equals("OG"))
         {
            // Orgnization EmailFormate
            msgbody = "\n" + //
                    "<!DOCTYPE html>\n" + //
                    "<html lang=\"en\">\n" + //
                    "\n" + //
                    "<head>\n" + //
                    "    <meta charset=\"UTF-8\">\n" + //
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" + //
                    "    <title>Login1</title>\n" + //
                    "</head>\n" + //
                    "\n" + //
                    "<body style=\"background-color: rgb(236, 239, 242); font-family: Arial, sans-serif; margin: 0; padding: 0;\">\n" + //
                    "\n" + //
                    "    <img src=\"https://res.cloudinary.com/dvln9maxh/image/upload/v1702210266/x4nnb4vpsv4bcw8mkzlf.jpg\"\n" + //
                    "        style=\"opacity: 1; margin: 10px auto; display: block; width: 200px; height: auto; max-width: 100%; margin-top: 40px ; \"\n" + //
                    "        onmouseover=\"this.style.opacity = '1.0';\" onmouseout=\"this.style.opacity = '1';\">\n" + //
                    "\n" + //
                    "    <div\n" + //
                    "        style=\"background-color: white; margin: 10px auto; width: 80%; max-width: 600px; height: auto; margin-top: 40px; padding: 20px; font-size: 1.2em; border-radius: 5px;\">\n" + //
                    "\n" + //
                    "        <h1 style=\"text-align: center;\">Welcome To ExamEasy</h1>\n" + //
                    "\n" + //
                    "        <p>We're here to help you create top-notch assessments. Dive into our tools for customizing assessments,\n" + //
                    "            analyzing performance, and achieving better learning outcomes.</p>\n" + //
                    "\n" + //
                    "        <p><b>Log in with your credentials and let's get started on your assessment journey!</b></p>\n" + //
                    "\n" + //
                    "        <h4>Need a hand? Reach us at <a href=\"exameasy.official@gmail.com\">exameasy.official@gmail.com</a></h4>\n" + //
                    "\n" + //
                    "        <p>Cheers,<br>Team ExamEasy</p>\n" + //
                    "    </div>\n" + //
                    "\n" + //
                    "    <style>\n" + //
                    "        @media only screen and (max-width: 600px) {\n" + //
                    "            img {\n" + //
                    "                width: 100%;\n" + //
                    "                margin-top: 20px;\n" + //
                    "            }\n" + //
                    "\n" + //
                    "            div {\n" + //
                    "                width: 90%;\n" + //
                    "                font-size: 1em;\n" + //
                    "            }\n" + //
                    "\n" + //
                    "            h1 {\n" + //
                    "                font-size: 1.5em;\n" + //
                    "            }\n" + //
                    "        }\n" + //
                    "    </style>\n" + //
                    "</body>\n" + //
                    "\n" + //
                    "</html>\n" + //
                    "";

        } else {
            msgbody = msg;
        }

 */