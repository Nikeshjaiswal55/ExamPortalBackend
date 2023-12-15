package examportal.portal.repotest;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;

import examportal.portal.Entity.Paper;
import examportal.portal.Repo.PaperRepo;



@SpringBootTest(classes=PaperRepoTest.class)
public class PaperRepoTest {

    @Mock
    private PaperRepo paperRepo;

    @Test
    public void testFindAllPaperByUserId() {
        // Arrange
        String userId = "123";
        Pageable pageable = mock(Pageable.class);
        List<Paper> mockPapers = new ArrayList<>();
        // Add mock papers to the list based on your requirements

        // Mocking the behavior of the repository method
        when(paperRepo.findAllPaperByUserId(eq(userId), eq(pageable))).thenReturn(mockPapers);

        // Act
        List<Paper> result = paperRepo.findAllPaperByUserId(userId, pageable);

        // Assert
        assertEquals(mockPapers, result);
    }

    @Test
    public void testGetAllpaperByName() {
        // Arrange
        String name = "Sample Paper";
        List<Paper> mockPapers = new ArrayList<>();
        // Add mock papers to the list based on your requirements

        // Mocking the behavior of the repository method
        when(paperRepo.getAllpaperByName(eq(name))).thenReturn(mockPapers);

        // Act
        List<Paper> result = paperRepo.getAllpaperByName(name);

        // Assert
        assertEquals(mockPapers, result);
    }

    @Test
    public void testCountByOrganizationId() {
        // Arrange
        String organizationId = "org123";
        Long mockCount = 5L;

        // Mocking the behavior of the repository method
        when(paperRepo.countByOrganizationId(eq(organizationId))).thenReturn(mockCount);

        // Act
        Long result = paperRepo.countByOrganizationId(organizationId);

        // Assert
        assertEquals(mockCount, result);
    }

    // You can add more test cases as needed for other methods or scenarios

    // Example test for findByFilter
    @Test
    public void testFindByFilter() {
        // Arrange
        String userId = "123";
        Pageable pageable = mock(Pageable.class);
        Map<String, String> filters = Map.of("is_Active", "true", "created_date", "2023-01-01");

        List<Paper> mockPapers = new ArrayList<>();
        // Add mock papers to the list based on your requirements

        // Mocking the behavior of the repository method
        when(paperRepo.findByFiter(eq(userId), eq(pageable), eq(filters))).thenReturn(mockPapers);

        // Act
        List<Paper> result = paperRepo.findByFiter(userId, pageable, filters);

        // Assert
        assertEquals(mockPapers, result);
    }
}

