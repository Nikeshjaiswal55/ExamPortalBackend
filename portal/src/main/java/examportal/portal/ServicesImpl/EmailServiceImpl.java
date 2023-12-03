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

    public String sendFormateMail(String to, String msg, String sub, String role) {
        log.info("EmailServiceImp , sendFormateMail Method Start");
        String msgbody = "";
        if (role.equals("Student")) {
            msgbody = "<!DOCTYPE html>" +
                    "<html lang='en'>" +
                    "<head>" +
                    "   <meta charset='UTF-8'>" +
                    "   <meta name='viewport' content='width=device-width, initial-scale=1.0'>" +
                    "   <title>ResetPassword</title>" +
                    "</head>" +
                    "<body style='background-color: rgb(236,239,242);'>" +
                    "   <img src='Screenshot 2023-11-06 135013.png' style='opacity: 1; margin: 10px auto; display: block; background-color: rgb(236,239,242);' onmouseover='this.style.opacity = \"1.0\";' onmouseout='this.style.opacity = \"1\";'>"
                    +
                    "   <div class='text' style='background-color: white; margin: 10px; margin:10px auto; width: 40%; height: 60%; margin-top: 5px; padding: 5px 40px; font-size: 1.2em; font-style: unset; border-radius: 5px;'>"
                    +
                    "       <h1>Hi Student!!</h1>" +
                    "       <p><b>you got assisstment!!</b></p>" +
                    "<p><b>Please check, below there were a credentials to login on ExamEasy</b></p>" +
                    "<p>" + msg + "</p>" +
                    "       <a href='http://localhost:5173/'><button type='button' class='reset-btn' style='background-color: black; color: white; display: block; margin: auto; border-radius: 10px; padding: 10px 30px;'>Log In</button></a>"
                    +
                    "       <p>if you face any issue, please contact us immediately at <a href='mailto:exameasy.offical@gmail.com'>exameasy.offical@gmail.com</a></p>"
                    +
                    "       <p>Thanks you,</p>" +
                    "       <p>Team ExamEasy</b></p>" +
                    "   </div>" +
                    "   <div class='bottom' style='height: 20px; background-color: rgb(236,239,242); text-align: center;'>"
                    +
                    "       Minimos | Moi Avenue CBD, Nairobi Kenya" +
                    "       <div style='background-color: rgb(236,239,242);'>" +
                    "           <a class='bottom-text' href='#' style='display: inline; margin: 0 10px; text-decoration: none; color: black;'>Terms of use</a>"
                    +
                    "           <a class='bottom-text' href='#' style='display: inline; margin: 0 10px; text-decoration: none; color: black;'>Privacy policy</a>"
                    +
                    "           <div class='social-icons' style='display: flex; justify-content: center; margin-top: 10px; background-color: rgb(236,239,242);'>"
                    +
                    "               <a href='#' class='s-icon-a' style='background-color: rgb(236,239,242) !important; margin: 0 10px; padding: 5px;'><img src='' alt='Facebook' width='40px' height='50px' style='border-radius: 100px;'></a>"
                    +
                    "               <a href='#' class='s-icon-a' style='background-color: rgb(236,239,242) !important; margin: 0 10px; padding: 5px;'><img src='square-instagram.svg' alt='Instagram' width='40px' height='50px' style='border-radius: 100px;'></a>"
                    +
                    "               <a href='#' class='s-icon-a' style='background-color: rgb(236,239,242) !important; margin: 0 10px; padding: 5px;'><img src='twitter.svg' alt='Twitter' width='40px' height='50px' style='border-radius: 100px;'></a>"
                    +
                    "           </div>" +
                    "       </div>" +
                    "   </div>" +
                    "   <style>" +
                    "       @media screen and (max-width:780px) {" +
                    "           .text {" +
                    "               width: 100%;" +
                    "               box-sizing: border-box;" +
                    "           }" +
                    "       }" +
                    "   </style>" +
                    "</body>" +
                    "</html>";

            ;
        } else {
            // abhi ke liye
            // html body for orginzation
            msgbody = msg;
        }

        try {

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
            helper.setTo(to);
            helper.setSubject(sub);

            helper.setText(msgbody, true);
            javaMailSender.send(message);
            log.info("EmailServiceImp , sendFormateMail Method Start");
            return "Mail Succesfully to" + to;
        } catch (Exception e) {
            System.out.println(e);
            return "Mail Not Done ";
        }
    }
}
