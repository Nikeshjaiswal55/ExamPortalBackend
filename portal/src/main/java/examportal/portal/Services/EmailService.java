package examportal.portal.Services;

import examportal.portal.Payloads.EmailDetails;

public interface EmailService {

      String sendSimpleMail(EmailDetails details); 

      String sendFormateMail(String to, String subject);
    
}
