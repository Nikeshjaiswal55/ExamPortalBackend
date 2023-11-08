package examportal.portal.Services;

import examportal.portal.Payloads.EmailDetails;

public interface EmailService {

      String sendSimpleMail(EmailDetails details); 

      String SendStyledMail(EmailDetails d);
    // Method
    // To send an email with attachment
  //  String sendMailWithAttachment(EmailDetails details);
}
