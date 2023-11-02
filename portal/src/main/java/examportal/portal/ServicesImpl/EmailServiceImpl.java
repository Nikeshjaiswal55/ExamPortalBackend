package examportal.portal.ServicesImpl;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.stereotype.Service;

import examportal.portal.DTO.EmailDetails;
import examportal.portal.Services.EmailService;


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
            SimpleMailMessage mailMessage
                = new SimpleMailMessage();
 
            // Setting up necessary details
            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipient());
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

    // mail with attachement
    // @Override
    // public String sendMailWithAttachment(EmailDetails details) {
    //       MimeMessage mimeMessage
    //         = javaMailSender.createMimeMessage();
    //     MimeMessageHelper mimeMessageHelper;
 
    //     try {
 
    //         // Setting multipart as true for attachments to
    //         // be send
    //         mimeMessageHelper
    //             = new MimeMessageHelper(mimeMessage, true);
    //         mimeMessageHelper.setFrom(sender);
    //         mimeMessageHelper.setTo(details.getRecipient());
    //         mimeMessageHelper.setText(details.getMsgBody());
    //         mimeMessageHelper.setSubject(
    //             details.getSubject());
 
    //         // Adding the attachment
    //         FileSystemResource file
    //             = new FileSystemResource(
    //                 new File(details.getAttachment()));
 
    //         mimeMessageHelper.addAttachment(
    //             file.getFilename(), file);
 
    //         // Sending the mail
    //         javaMailSender.send(mimeMessage);
    //         return "Mail sent Successfully";
    //     }
 
    //     // Catch block to handle MessagingException
    //     catch (MessagingException e) {
 
    //         // Display message when exception occurred
    //         return "Error while sending mail!!!";
    //   
  
        
        
    
    
}
