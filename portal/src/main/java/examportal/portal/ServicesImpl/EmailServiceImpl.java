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

    // private final TemplateEngine templateEngine =;


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
    public String SendStyledMail(EmailDetails details) {
        System.out.println("enter In SendStyledMail +++++++++++++++++++++++++++++++++++++++++++++++++");
        String msgbody = "<!DOCTYPE html>\r\n" + //
                "<html lang=\"en\">\r\n" + //
                "\r\n" + //
                "<head>\r\n" + //
                "    <meta charset=\"UTF-8\">\r\n" + //
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + //
                "    <title>Document</title>\r\n" + //
                "</head>\r\n" + //
                "\r\n" + //
                "<body style=\"background-color: rgb(236, 239, 242);\">\r\n" + //
                "    <img src=\"Screenshot 2023-11-06 135013.png\" style=\"opacity: 1; margin: 10px auto; display: block; background-color: rgb(236, 239, 242);\">\r\n" + //
                "    <div style=\"background-color: white; margin: 10px; margin: 10px auto; width: 40%; height: 60%; margin-top: 5px; padding: 5px 40px; font-size: 1.2em; font-style: unset; border-radius: 5px;\">\r\n" + //
                "        <h1>Hi Krishna,</h1>\r\n" + //
                "        <p><b>Need to reset your password? No problem. just click the button below and you will be on your way </b></p>\r\n" + //
                "        <button type=\"button\" style=\"background-color: black; color: white; display: block; margin: auto; border-radius: 10px; padding: 10px 30px;\">Reset password</button>\r\n" + //
                "        <p>if you did not initiate this request, please contact us immediately at <a href=\"\" style=\"text-decoration: none; color: black;\">Support@appname.com</a></p>\r\n" + //
                "        <p> Thanks you,</p>\r\n" + //
                "        <p>Team Minimos</p>\r\n" + //
                "    </div>\r\n" + //
                "\r\n" + //
                "    <div style=\"height: 20px; background-color: rgb(236, 239, 242); text-align: center;\">\r\n" + //
                "        Minimos | Moi Avenue CBD, Nairobi Kenya\r\n" + //
                "        <div style=\"background-color: rgb(236, 239, 242);\">\r\n" + //
                "            <a style=\"margin: 0 10px; text-decoration: none; color: black;\" href=\"#\">Terms of use</a>\r\n" + //
                "            <a style=\"margin: 0 10px; text-decoration: none; color: black;\" href=\"#\">Privacy policy</a>\r\n" + //
                "\r\n" + //
                "            <div style=\"display: flex; justify-content: center; margin-top: 10px; background-color: rgb(236, 239, 242);\">\r\n" + //
                "                <a style=\"background-color: rgb(236, 239, 242); margin: 0 10px; padding: 5px;\"><img src=\"facebook.svg\" alt=\"Facebook\" width=\"40px\" height=\"50px\" style=\"border-radius: 100px;\"></a>\r\n" + //
                "                <a style=\"background-color: rgb(236, 239, 242); margin: 0 10px; padding: 5px;\"><img src=\"square-instagram.svg\" alt=\"Instagram\" width=\"40px\" height=\"50px\" style=\"border-radius: 100px;\"></a>\r\n" + //
                "                <a style=\"background-color: rgb(236, 239, 242); margin: 0 10px; padding: 5px;\"><img src=\"twitter.svg\" alt=\"Twitter\" width=\"40px\" height=\"50px\" style=\"border-radius: 100px;\"></a>\r\n" + //
                "            </div>\r\n" + //
                "        </div>\r\n" + //
                "    </div>\r\n" + //
                "</body>\r\n" + //
                "\r\n" + //
                "</html>\r\n" + //
                "";

try {
    
    MimeMessage message = javaMailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
    helper.setTo(details.getTo());
    helper.setSubject(details.getSubject());
    // message.setContent(, msgbody);
    helper.setText(msgbody, true);
    javaMailSender.send(message);

    return "Mail Succesfully to"+ details.getTo();
} catch (Exception e) {
    System.out.println(e);
    return "Mail Not Done ";
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
