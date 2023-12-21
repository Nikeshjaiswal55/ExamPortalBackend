package examportal.portal.RepoTest;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import examportal.portal.Entity.Paper;
import examportal.portal.Repo.PaperRepo;


@SpringBootTest(classes=StudentRepoTest.class)
public class PaperRepoTest {

    @Mock
    private PaperRepo paperRepo;

    @Test
    public void testFindAllPaperByUserId() {
        
        String userId = "testUserId";
        Pageable pageable = mock(Pageable.class);
        List<Paper> mockPapers = new ArrayList<>(); // Add some mock papers
        Page<Paper> mockPaperPage = mock(Page.class);
        when(paperRepo.findAllPaperByUserId(userId, pageable)).thenReturn(mockPaperPage);
        when(mockPaperPage.getContent()).thenReturn(mockPapers);

      
        Page<Paper> result = paperRepo.findAllPaperByUserId(userId, pageable);

        assertEquals(mockPapers, result.getContent());
    }

    @Test
    public void testCountByOrganizationId() {
      
        String organizationId = "testOrgId";
        Long expectedCount = 10L;
        when(paperRepo.countByOrganizationId(organizationId)).thenReturn(expectedCount);

     
        Long result = paperRepo.countByOrganizationId(organizationId);

        assertEquals(expectedCount, result);
    }


    @Test
    public void testFindByFilter() {
    
        String userId = "testUserId";
        Pageable pageable = mock(Pageable.class);
        Map<String, String> filters = new HashMap<>();
        filters.put("is_Active", "true");
        filters.put("created_date", "2023-01-01");
        filters.put("published_date", "2023-01-10");
        filters.put("paper_name", "Test Paper");
        
        List<Paper> mockPapers = new ArrayList<>(); // Add some mock papers
        Page<Paper> mockPaperPage = mock(Page.class);
        when(paperRepo.findByFiter(userId, pageable, filters)).thenReturn(mockPaperPage);
        when(mockPaperPage.getContent()).thenReturn(mockPapers);

      
        Page<Paper> result = paperRepo.findByFiter(userId, pageable, filters);

        assertEquals(mockPapers, result.getContent());
    }

    
}
