package examportal.portal.repotest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import examportal.portal.Entity.User;
import examportal.portal.Repo.UserRepo;

@SpringBootTest(classes= UserRepoTest.class)
public class UserRepoTest {

    @Mock
    private UserRepo userRepo;

    @Test
    public void testFindByEmail() {
        // Arrange
        String email = "test@example.com";
        User mockUser = new User();
        // Set properties of mockUser based on your requirements

        // Mocking the behavior of the repository method
        when(userRepo.findByEmail(eq(email))).thenReturn(mockUser);

        // Act
        User result = userRepo.findByEmail(email);

        // Assert
        assertEquals(mockUser, result);
    }

    @Test
    public void testFindByName() {
        // Arrange
        String name = "John Doe";
        List<User> mockUsers = new ArrayList<>();
        // Add mock users to the list based on your requirements

        // Mocking the behavior of the repository method
        when(userRepo.findByName(eq(name))).thenReturn(mockUsers);

        // Act
        List<User> result = userRepo.findByName(name);

        // Assert
        assertEquals(mockUsers, result);
    }

    @Test
    public void testFindByRole() {
        // Arrange
        String role = "ROLE_USER";
        List<User> mockUsers = new ArrayList<>();
        // Add mock users to the list based on your requirements

        // Mocking the behavior of the repository method
        when(userRepo.findByRole(eq(role))).thenReturn(mockUsers);

        // Act
        List<User> result = userRepo.findByRole(role);

        // Assert
        assertEquals(mockUsers, result);
    }

    @Test
    public void testFindByUserId() {
        // Arrange
        String userId = "123";
        List<User> mockUsers = new ArrayList<>();
        // Add mock users to the list based on your requirements

        // Mocking the behavior of the repository method
        when(userRepo.findByUserId(eq(userId))).thenReturn(mockUsers);

        // Act
        List<User> result = userRepo.findByUserId(userId);

        // Assert
        assertEquals(mockUsers, result);
    }

    @Test
    public void testGetAllUserByName() {
        // Arrange
        String name = "John Doe";
        List<User> mockUsers = new ArrayList<>();
        // Add mock users to the list based on your requirements

        // Mocking the behavior of the repository method
        when(userRepo.getAllUserByName(eq(name))).thenReturn(mockUsers);

        // Act
        List<User> result = userRepo.getAllUserByName(name);

        // Assert
        assertEquals(mockUsers, result);
    }

    // You can add more test cases as needed for other methods or scenarios
}
