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

import examportal.portal.Entity.Orgnizations;
import examportal.portal.Repo.OrgnizationRepo;

@ExtendWith(MockitoExtension.class)

@SpringBootTest(classes=OrgnizationRepoTests .class)
public class OrgnizationRepoTests {

    @Mock
    private OrgnizationRepo orgnizationRepo;

   
    @Test
    public void testGetAllOrgnizationByUserID() {
      
        String userId = "yourUserId";
        Orgnizations expectedOrgnization = new Orgnizations(/* initialize with data */);

        when(orgnizationRepo.getAllOrgnizationByUserID(userId)).thenReturn(expectedOrgnization);

        
        Orgnizations actualOrgnization = orgnizationRepo.getAllOrgnizationByUserID(userId);

        
        assertEquals(expectedOrgnization, actualOrgnization);
    }

    @Test
    public void testFindByOrgnizationId() {
      
        String orgnizationId = "yourOrgnizationId";
        Orgnizations orgnization1 = new Orgnizations(/* initialize with data */);
        Orgnizations orgnization2 = new Orgnizations(/* initialize with data */);
        List<Orgnizations> expectedOrgnizations = Arrays.asList(orgnization1, orgnization2);

        when(orgnizationRepo.findByOrgnizationId(orgnizationId)).thenReturn(expectedOrgnizations);

       
        List<Orgnizations> actualOrgnizations = orgnizationRepo.findByOrgnizationId(orgnizationId);

       
        assertEquals(expectedOrgnizations, actualOrgnizations);
    }
}
