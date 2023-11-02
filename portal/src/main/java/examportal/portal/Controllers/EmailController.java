package examportal.portal.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import examportal.portal.DTO.EmailDetails;
import examportal.portal.Services.EmailService;

@RestController
public class EmailController {

     @Autowired private EmailService emailService;
 
      // Sending a simple Email
    @PostMapping("/sendMail")
    public String
    sendMail(@RequestBody EmailDetails details)
    {
        String status
            = emailService.sendSimpleMail(details);
 
        return status;
    }

    // @PostMapping("/sendMailWithAttachment")
    // public String sendMailWithAttachment(
    //     @RequestBody EmailDetails details)
    // {
    //     String status
    //         = emailService.sendMailWithAttachment(details);
 
    //     return status;
    // }
 
}
