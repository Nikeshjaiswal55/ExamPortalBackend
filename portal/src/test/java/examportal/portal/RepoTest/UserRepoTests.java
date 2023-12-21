package examportal.portal.RepoTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import examportal.portal.Entity.User;
import examportal.portal.Repo.UserRepo;


@SpringBootTest(classes=UserRepoTests .class)
@ExtendWith(MockitoExtension.class)

public class UserRepoTests {

    @Mock
    private UserRepo userRepo;

    
    @Test
    public void testFindByEmail() {
     
        String email = "test@example.com";
        User expectedUser = new User();

        when(userRepo.findByEmail(email)).thenReturn(expectedUser);

     
        User actualUser = userRepo.findByEmail(email);

       
        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void testFindByName() {
       
        String name = "John Doe";
        User user1 = new User(/* initialize with data */);
        User user2 = new User(/* initialize with data */);
        List<User> expectedUsers = Arrays.asList(user1, user2);

        when(userRepo.findByName(name)).thenReturn(expectedUsers);

    
        List<User> actualUsers = userRepo.findByName(name);

    
        assertEquals(expectedUsers, actualUsers);
    }

    @Test
    public void testFindByRole() {
      
        String role = "ROLE_USER";
        User user1 = new User(/* initialize with data */);
        User user2 = new User(/* initialize with data */);
        List<User> expectedUsers = Arrays.asList(user1, user2);

        when(userRepo.findByRole(role)).thenReturn(expectedUsers);

      
        List<User> actualUsers = userRepo.findByRole(role);

        assertEquals(expectedUsers, actualUsers);
    }

    @Test
    public void testFindByUserId() {
       
        String userId = "123";
        User user1 = new User(/* initialize with data */);
        User user2 = new User(/* initialize with data */);
        List<User> expectedUsers = Arrays.asList(user1, user2);

        when(userRepo.findByUserId(userId)).thenReturn(expectedUsers);

     
        List<User> actualUsers = userRepo.findByUserId(userId);

        
        assertEquals(expectedUsers, actualUsers);
    }
}

