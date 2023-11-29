 package examportal.portal.ServicesImpl;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
// import org.thymeleaf.TemplateEngine;

import examportal.portal.Payloads.EmailDetails;
import examportal.portal.Services.EmailService;
import jakarta.mail.internet.MimeMessage;
 
@Service
public class EmailServiceImpl implements EmailService{

    Logger log = org.slf4j.LoggerFactory.getLogger("EmailServiceImpl.class");

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;
    @Override
    public String sendSimpleMail(EmailDetails details) {
        log.info("EmailSeviceImpl , sendSimpleMail Method Start");
         // Try block to check for exceptions
        try {
            // Creating a simple mail message
            SimpleMailMessage mailMessage= new SimpleMailMessage();
 
            // Setting up necessary details
            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getTo());
            mailMessage.setText(details.getMsgBody());
            mailMessage.setSubject(details.getSubject());
 
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
    @Override
    public String sendFormateMail
    (EmailDetails details) {
        System.out.println("enter In SendStyledMail +++++++++++++++++++++++++++++++++++++++++++++++++");
        String msgbody = details.getMsgBody();

try {
    
    MimeMessage message = javaMailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
    helper.setTo(details.getTo());
    helper.setSubject(details.getSubject());
    
    helper.setText(msgbody, true);
    javaMailSender.send(message);

    return "Mail Succesfully to"+ details.getTo();
} catch (Exception e) {
    System.out.println(e);
    return "Mail Not Done ";
}
       
    }


    
}
