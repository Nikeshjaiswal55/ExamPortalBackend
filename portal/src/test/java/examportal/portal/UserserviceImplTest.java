package examportal.portal;

 

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import examportal.portal.Entity.User;
import examportal.portal.Exceptions.ResourceAlreadyExistException;
import examportal.portal.Exceptions.ResourceNotFoundException;
import examportal.portal.Payloads.EmailDetails;
import examportal.portal.Payloads.userDto;
import examportal.portal.Repo.UserRepo;
import examportal.portal.Services.EmailService;
import examportal.portal.Services.UserService;
import examportal.portal.ServicesImpl.UserserviceImpl;


 @SpringBootTest(classes = UserserviceImplTest.class)

class UserserviceImplTest {

    @Mock
    private UserRepo userRepo;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private UserserviceImpl userService;

    @Test
    @Deprecated
    void testCreateUser_Success() {
        // Arrange
        userDto newUserDto = new userDto("1", "john@example.com", "John Doe", "null", "null", "null", "a");
        User savedUser = new User("1", "john@example.com", "John Doe", "null", "null", "null", "null");

        when(userRepo.findByEmail(newUserDto.getEmail())).thenReturn(null);
        when(userRepo.save(any(User.class))).thenReturn(savedUser);

        // Act
        User result = userService.createUser(newUserDto);

        // Assert
        assertEquals(savedUser, result);
        verify(userRepo, times(1)).findByEmail(newUserDto.getEmail());
        // verify(userRepo, times(1)).save(any(User.class));
         verify(userRepo, atLeastOnce()).save(any(User.class));
        verify(emailService, times(1)).sendSimpleMail(any(EmailDetails.class));
    }

    @Test
    void testCreateUser_EmailAlreadyExists() {
        // Arrange
        userDto existingUserDto = new userDto("1", "john@example.com", "John Doe", "null", "null", "null", "a");
        User existingUser = new User("1", "john@example.com", "John Doe", "null", "null", "null", "null");

        when(userRepo.findByEmail(existingUserDto.getEmail())).thenReturn(existingUser);

        // Act & Assert
        assertThrows(ResourceAlreadyExistException.class, () -> userService.createUser(existingUserDto));

        // Verify
        verify(userRepo, times(1)).findByEmail(existingUserDto.getEmail());
        verify(userRepo, never()).save(any(User.class));
        verify(emailService, never()).sendSimpleMail(any(EmailDetails.class));
    }

    @Test
    void testGetAllUser() {
        // Arrange
        User mockUser1 = new User("1", "john_doe", "john@example.com", "password123", "null", "null", "null");
        User mockUser2 = new User("2", "jane_doe", "jane@example.com", "password456", "null", "null", "null");

        List<User> mockUsers = Arrays.asList(mockUser1, mockUser2);

        when(userRepo.findAll()).thenReturn(mockUsers);

        // Act
        List<User> result = userService.getAllUser();

        // Assert
        assertEquals(mockUsers, result);
        verify(userRepo, times(1)).findAll();
    }

    @Test
    void testGetUserById_UserExists() {
        // Arrange
        String userId = "1";
        User user = new User("1", "john", "john@example.com", "password123", "null", "nulll", "a");

        when(userRepo.findById(userId)).thenReturn(java.util.Optional.of(user));

        // Act
        User result = userService.getUserById(userId);

        // Assert
        assertEquals(user, result);
        verify(userRepo, times(1)).findById(userId);
    }

    @Test
    void testGetUserById_UserNotFound() {
        // Arrange
        String userId = "999";

        when(userRepo.findById(userId)).thenReturn(java.util.Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> userService.getUserById(userId));

        // Verify
        verify(userRepo, times(1)).findById(userId);
    }
}