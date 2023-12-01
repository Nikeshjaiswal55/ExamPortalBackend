package examportal.portal.Services;

import java.util.List;

import examportal.portal.Entity.User;
import examportal.portal.Payloads.PageableDto;
import examportal.portal.Payloads.userDto;
import examportal.portal.Response.PageResponce;

public  interface UserService {
    
    User createUser(userDto user);
    
    
    PageResponce getAllUser(PageableDto dto);

    

    
}