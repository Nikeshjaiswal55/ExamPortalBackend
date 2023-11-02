package examportal.portal.Services;

import examportal.portal.DTO.EmailDetails;

public interface EmailService {

      String sendSimpleMail(EmailDetails details); 
    // Method
    // To send an email with attachment
  //  String sendMailWithAttachment(EmailDetails details);
}
