// package examportal.portal;

// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.Mockito.times;
// import static org.mockito.Mockito.verify;
// import static org.mockito.Mockito.when;
// import org.springframework.mail.javamail.JavaMailSender;

// import examportal.portal.ServicesImpl.EmailServiceImpl;
// import jakarta.mail.internet.MimeMessage;

// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.ArgumentCaptor;
// import org.mockito.Captor;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.Mockito;
// import org.mockito.junit.jupiter.MockitoExtension;
// import org.slf4j.Logger;
// import org.springframework.mail.SimpleMailMessage;
// import org.springframework.mail.javamail.JavaMailSender;

// @ExtendWith(MockitoExtension.class)
// public class EmailServiceImplTest {

//     @InjectMocks
//     private EmailServiceImpl emailService;

//     @Mock
//     private JavaMailSender javaMailSender;

//     @Mock
//     private Logger logger;
    

//    @Captor
//     private ArgumentCaptor<MimeMessage> mimeMessageCaptor;


//     @Test
//     void testSendSimpleMail() {
//         String to = "test@example.com";
//         String msg = "Test Message";
//         String sub = "Test Subject";

//         emailService.sendSimpleMail(to, msg, sub);

//         SimpleMailMessage expectedMessage = new SimpleMailMessage();
//         expectedMessage.setFrom(emailService.getSender());
//         expectedMessage.setTo(to);
//         expectedMessage.setText(msg);
//         expectedMessage.setSubject(sub);

//         verify(javaMailSender, times(1)).send(expectedMessage);
//         verify(logger, times(1)).info("EmailSeviceImpl , sendSimpleMail Method Ends");
//     }


//     @Test
//     void testSendFormateMailForStudent() {
//         String to = "student@example.com";
//         String msg = "Test Message";
//         String sub = "Test Subject";
//         String role = "Student";

//         emailService.sendFormateMail(to, msg, sub, role);

//         // Assert your expectations for the HTML body here

//         verify(javaMailSender, times(1)).send(any());
//         verify(logger, times(1)).info("EmailServiceImp , sendFormateMail Method End");
//     }


//     @Test
//     void testSendFormateMailForOtherRoles() {
//         String to = "org@example.com";
//         String msg = "Test Message";
//         String sub = "Test Subject";
//         String role = "OtherRole";

//         emailService.sendFormateMail(to, msg, sub, role);

//         // Verify the send method with a MimeMessage argument
//         verify(javaMailSender, times(1)).send(mimeMessageCaptor.capture());

//         // Debugging: Log or print the captured MimeMessage
//         MimeMessage capturedMessage = mimeMessageCaptor.getValue();
//         System.out.println("Captured MimeMessage: " + capturedMessage);

//         // Perform additional assertions on the captured MimeMessage if necessary

//         verify(logger, times(1)).info("EmailServiceImp , sendFormateMail Method End");
//     }
// }








