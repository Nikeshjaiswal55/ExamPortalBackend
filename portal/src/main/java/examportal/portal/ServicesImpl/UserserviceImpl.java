package examportal.portal.ServicesImpl;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.stereotype.Service;

import examportal.portal.Entity.User;
import examportal.portal.Exceptions.ResourceAlreadyExistException;
import examportal.portal.Payloads.EmailDetails;
import examportal.portal.Payloads.PageableDto;
import examportal.portal.Payloads.userDto;
import examportal.portal.Repo.UserRepo;
import examportal.portal.Response.PageResponce;
import examportal.portal.Services.EmailService;
import examportal.portal.Services.UserService;

@Service
public class UserserviceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private EmailService emailServices;

    Logger log = LoggerFactory.getLogger(UserserviceImpl.class);

    @Deprecated
    @Override
    public User createUser(userDto user) {

        log.info("UserserviceImpl , createUser Method Start");

        User findUser = this.userRepo.findByEmail(user.getEmail());

        if (findUser != null) {
            throw new ResourceAlreadyExistException("user", "email", user.getEmail());
        } else {
            User newuser = new User();
            newuser.setUserId(user.getUserId());
            newuser.setEmail(user.getEmail());
            newuser.setName(user.getName());
            newuser.setPicture(user.getPicture());
            newuser.setUpdatedAt(user.getUpdatedAt());
            newuser.setRole(user.getRole());
            User saveduser = this.userRepo.save(newuser);
            // creating user in auth0 with api only for testing
            // try {
            // this.auth0Service.createUser(saveduser.getEmail(), user.getName()+"123",
            // user.getToken());
            // } catch (Exception e) {
            // e.printStackTrace();
            // }
            // creating user in auth0 with api only for testing
            sendmail(saveduser);
            log.info("UserserviceImpl , createUser Method Ends");

            return saveduser;
        }

    }

    // @Override
    public String sendmail(User user) {

        log.info("UserserviceImpl , Send Email Method Start");
        String message = "<!DOCTYPE html>\n" + //
                "<html lang=\"en\">\n" + //
                "<head>\n" + //
                "    <meta charset=\"UTF-8\">\n" + //
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" + //
                "    <title>ResetPassword</title>\n" + //
                "</head>\n" + //
                "<body style=\"background-color: rgb(236,239,242);\">\n" + //
                "    <img src=\"Screenshot 2023-11-06 135013.png\" style=\"opacity: 1; margin: 10px auto; display: block; background-color: rgb(236,239,242);\" onmouseover=\"this.style.opacity = '1.0';\" onmouseout=\"this.style.opacity = '1';\">\n" + //
                "    <div class=\"text\" style=\"background-color: white; margin: 10px; margin:10px auto; width: 40%; height: 60%; margin-top: 5px; padding: 5px 40px; font-size: 1.2em; font-style: unset; border-radius: 5px;\">\n" + //
                "        <h1>Hi John,</h1>\n" + //
                "        <p><b>Need to reset your password? No problem. just click the button below and you will be on your way</p>\n" + //
                "        <button type=\"button\" class=\"reset-btn\" style=\"background-color: black; color: white; display: block; margin: auto; border-radius: 10px; padding: 10px 30px;\">Reset password</button>\n" + //
                "        <p>if you did not initiate this request, please contact us immediately at <a href=\"\">Support@appname.com</a></p>\n" + //
                "        <p>Thanks you,</p>\n" + //
                "        <p>Team Minimos</b></p>\n" + //
                "    </div>\n" + //
                "\n" + //
                "    <div class=\"bottom\" style=\"height: 20px; background-color: rgb(236,239,242); text-align: center;\">\n" + //
                "        Minimos | Moi Avenue CBD, Nairobi Kenya\n" + //
                "        <div style=\"background-color: rgb(236,239,242);\">\n" + //
                "            <a class=\"bottom-text\" href=\"#\" style=\"display: inline; margin: 0 10px; text-decoration: none; color: black;\">Terms of use</a>\n" + //
                "            <a class=\"bottom-text\" href=\"#\" style=\"display: inline; margin: 0 10px; text-decoration: none; color: black;\">Privacy policy</a>\n" + //
                "\n" + //
                "            <div class=\"social-icons\" style=\"display: flex; justify-content: center; margin-top: 10px; background-color: rgb(236,239,242);\">\n" + //
                "                <a href=\"#\" class=\"s-icon-a\" style=\"background-color: rgb(236,239,242) !important; margin: 0 10px; padding: 5px;\"><img src=\"\" alt=\"Facebook\" width=\"40px\" height=\"50px\" style=\"border-radius: 100px;\"></a>\n" + //
                "                <a href=\"#\" class=\"s-icon-a\" style=\"background-color: rgb(236,239,242) !important; margin: 0 10px; padding: 5px;\"><img src=\"square-instagram.svg\" alt=\"Instagram\" width=\"40px\" height=\"50px\" style=\"border-radius: 100px;\"></a>\n" + //
                "                <a href=\"#\" class=\"s-icon-a\" style=\"background-color: rgb(236,239,242) !important; margin: 0 10px; padding: 5px;\"><img src=\"twitter.svg\" alt=\"Twitter\" width=\"40px\" height=\"50px\" style=\"border-radius: 100px;\"></a>\n" + //
                "            </div>\n" + //
                "        </div>\n" + //
                "    </div>\n" + //
                "    <style>\n" + //
                "        @media screen and (max-width:780px) {\n" + //
                "            .text {\n" + //
                "                width: 100%;\n" + //
                "                box-sizing: border-box;\n" + //
                "            }\n" + //
                "            /* .reset-btn{\n" + //
                "                margin-left: 20%;\n" + //
                "            } */\n" + //
                "        }\n" + //
                "    </style>\n" + //
                "</body>\n" + //
                "</html>";
        String subject = "signin";
        String to = user.getEmail();
        EmailDetails em = new EmailDetails(to, message, subject);
        emailServices.sendFormateMail(em);
        
        // System.out.println(save);
        log.info("UserserviceImpl , Sene EMail Method End's");
        return "Email send sucess fully";
    }

    @Override
    public PageResponce getAllUser(PageableDto dto) {
        log.info("UserserviceImpl , getAllUser Method Start");
        Sort sort;
        
        if(dto.getSortDirection().equals("DESC")){
            sort = Sort.by(dto.getProperty()).descending();
         
        }else{
            sort = Sort.by(dto.getProperty()).ascending();    
        }

        Pageable p = PageRequest.of(dto.getPageNo(), dto.getPageSize(), sort);
        Page<User> u1 = this.userRepo.findAll(p);
        // List<User> list = u1.getContent();
    
        PageResponce pr = new PageResponce();
        pr.setContent_User(u1.getContent());
        pr.setPage(u1.getNumber()+1);
        pr.setTotalElements(u1.getTotalElements());
        pr.setTotalPages(u1.getTotalPages());
        pr.setPagesize(u1.getSize());
        pr.setIslastPage(u1.isLast());
        pr.setSortby(dto.getProperty());
        pr.setSortDirection(dto.getSortDirection());
        
        log.info("UserserviceImpl , getAllUser Method Start");
        return pr;
    }

}