package examportal.portal.ServicesImpl;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
// import org.thymeleaf.TemplateEngine;

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

    public String sendFormateMail(String to, String msg, String sub) {
        System.out.println("enter In SendStyledMail +++++++++++++++++++++++++++++++++++++++++++++++++");
        String msgbody = msg;

        try {

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
            helper.setTo(to);
            helper.setSubject(sub);

            helper.setText(msgbody, true);
            javaMailSender.send(message);

            return "Mail Succesfully to" + to;
        } catch (Exception e) {
            System.out.println(e);
            return "Mail Not Done ";
        }

    }

}
