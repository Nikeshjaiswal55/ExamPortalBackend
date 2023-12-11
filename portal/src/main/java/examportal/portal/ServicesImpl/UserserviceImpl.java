package examportal.portal.ServicesImpl;
import java.util.List;
import java.util.NoSuchElementException;
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
import examportal.portal.Exceptions.ResourceNotFoundException;
import examportal.portal.Repo.UserRepo;
import examportal.portal.Services.UserService;

@Service
public class UserserviceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private EmailServiceImpl emailServiceImpl;

    Logger log = LoggerFactory.getLogger("userServiceImpl");

    @Deprecated
    @Override
    public User createUser(User user) {

        log.info("userService , createUser Method Start");

        User findUser = this.userRepo.findByEmail(savedUser2.getEmail());

        if (findUser != null) {
            throw new ResourceAlreadyExistException("user", "email", savedUser2.getEmail());
        } else {
            User saveduser = this.userRepo.save(user);
            if(saveduser.getRole().equals("OG")){
                 String msg = "Orginzation Created Succesfully by "+saveduser.getEmail();    
                 emailServiceImpl.sendFormateMail(saveduser.getEmail(), msg, "Orgnizaton Creation", saveduser.getRole());
            }
            log.info("userService , createUser Method Ends");

            return saveduser;
        }

    }


    @Override
    public List<User> getAllUser(Integer page,Integer size, String sortField, String sortOrder) {
        log.info("userService , getAllUser Method Start");
        Sort sort =(sortOrder.equalsIgnoreCase("asc"))?Sort.by(sortField).ascending():Sort.by(sortField).descending();
        Pageable p=PageRequest.of(page,size,sort);
        Page<User> pa =this.userRepo.findAll(p);
        List<User> u1 = pa.getContent();
        log.info("userService , getAllUser Method Start");
        return u1;
    }

    @Override
    public User getUserById(String userId) {
        log.info("userService , getAllUser Method Start");
        User user = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user", "userId", userId));
        log.info("userService , getAllUser Method Start");
        return user;
    }


    @Override
    public List<User> getAllUserByName(String name) {
        log.info("userService, getUserByName method Start");
         List<User>list =userRepo.getAllUserByName(name);
         if(list.isEmpty()){
            throw new NoSuchElementException(" student list is empty ");
         }
         log.info("userService , getallUserByname method and");
         return list;
    }

}