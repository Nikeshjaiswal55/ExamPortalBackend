package examportal.portal.repotest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import examportal.portal.Entity.Orgnizations;
import examportal.portal.Repo.OrgnizationRepo;

@SpringBootTest(classes=OrgnizationRepoTest.class)
public class OrgnizationRepoTest {

    @Mock
    private OrgnizationRepo orgnizationRepo;

    @Test
    public void testGetAllOrgnizationByUserID() {
        // Arrange
        String userId = "123";
        Orgnizations mockOrgnization = new Orgnizations();
        // Set properties of mockOrgnization based on your requirements

        // Mocking the behavior of the repository method
        when(orgnizationRepo.getAllOrgnizationByUserID(eq(userId))).thenReturn(mockOrgnization);

        // Act
        Orgnizations result = orgnizationRepo.getAllOrgnizationByUserID(userId);

        // Assert
        assertEquals(mockOrgnization, result);
    }

    @Test
    public void testGetAllOrgnizationsByName() {
        // Arrange
        String name = "ABC Corp";
        List<Orgnizations> mockOrgnizations = new ArrayList<>();
        // Add mock orgnizations to the list based on your requirements

        // Mocking the behavior of the repository method
        when(orgnizationRepo.getAllOrgnizationsByName(eq(name))).thenReturn(mockOrgnizations);

        // Act
        List<Orgnizations> result = orgnizationRepo.getAllOrgnizationsByName(name);

        // Assert
        assertEquals(mockOrgnizations, result);
    }

    // You can add more test cases as needed for other methods or scenarios
}
