package examportal.portal.ServiceImpTest;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import examportal.portal.Entity.Orgnizations;
import examportal.portal.Entity.User;
import examportal.portal.Exceptions.ResourceAlreadyExistException;
import examportal.portal.Exceptions.ResourceNotFoundException;
import examportal.portal.Payloads.OrgnizationDto;
import examportal.portal.Repo.OrgnizationRepo;
import examportal.portal.Services.UserService;
import examportal.portal.ServicesImpl.OrgnizationServiceImpl;
   
@SpringBootTest(classes= OrgnizationServiceImpTest.class)
class OrgnizationServiceImpTest {

    @Mock
    private OrgnizationRepo orgnizationRepo;

    @Mock
    private UserService userService;

    @InjectMocks
    private OrgnizationServiceImpl orgnizationService;

    @Test
    void testCreateOrgnizations() {
       
        OrgnizationDto orgnizationDto = new OrgnizationDto();
        orgnizationDto.setUserId("user123");
        orgnizationDto.setOrgnizationName("TestOrg");
        orgnizationDto.setOrgnizationType("TestType");

        when(orgnizationRepo.getAllOrgnizationByUserID("user123")).thenReturn(null);
        when(orgnizationRepo.save(any())).thenReturn(new Orgnizations());
        when(userService.createUser(any())).thenReturn(new User());

        Orgnizations result = orgnizationService.createOrgnizations(orgnizationDto);

        assertNotNull(result);
        assertNotNull(result.getOrgnizationId()); // Ensure orgnizationId is set
        verify(orgnizationRepo, times(1)).save(any());
        verify(userService, times(1)).createUser(any());
    }

    @Test
    void testCreateOrgnizationsAlreadyExists() {
      
        OrgnizationDto orgnizationDto = new OrgnizationDto();
        orgnizationDto.setUserId("user123");
        orgnizationDto.setOrgnizationName("TestOrg");
        orgnizationDto.setOrgnizationType("TestType");

        when(orgnizationRepo.getAllOrgnizationByUserID("user123")).thenReturn(new Orgnizations());

    
        assertThrows(ResourceAlreadyExistException.class, () -> orgnizationService.createOrgnizations(orgnizationDto));
        verify(orgnizationRepo, never()).save(any());
        verify(userService, never()).createUser(any());
    }

    @Test
    void testGetAllOrgnizations() {
      
        int pageNumber = 0;
        int size = 10;
        String sortField = "orgnizationName";
        String sortOrder = "ASC";

        List<Orgnizations> orgnizationsList = new ArrayList<>();
        orgnizationsList.add(new Orgnizations());
        Page<Orgnizations> orgnizationsPage = new PageImpl<>(orgnizationsList);

        when(orgnizationRepo.findAll(any(Pageable.class))).thenReturn(orgnizationsPage);

       
        List<Orgnizations> result = orgnizationService.getAllOrgnizations(pageNumber, size, sortField, sortOrder);

       
        assertEquals(orgnizationsList, result);
        verify(orgnizationRepo, times(1)).findAll(any(Pageable.class));
    }

    
     @Test
    void testUpdateOrgnizations() {
       
        Orgnizations orgnization = new Orgnizations();
        orgnization.setOrgnizationId("org123");
        orgnization.setOrgnizationName("OldName");
        orgnization.setOrgnizationType("OldType");

        when(orgnizationRepo.findById("org123")).thenReturn(Optional.of(orgnization));
        when(orgnizationRepo.save(any())).thenReturn(orgnization);

    
        Orgnizations updatedOrgnization = orgnizationService.updateOrgnizations(orgnization);

      
        assertNotNull(updatedOrgnization);
        assertEquals("org123", updatedOrgnization.getOrgnizationId());
   
        verify(orgnizationRepo, times(1)).save(any());
    }
     @Test
    void testUpdateOrgnizationsNotFound() {

        Orgnizations orgnization = new Orgnizations();
        orgnization.setOrgnizationId("nonexistentId");

        when(orgnizationRepo.findById("nonexistentId")).thenReturn(Optional.empty());

       
        assertThrows(ResourceNotFoundException.class, () -> orgnizationService.updateOrgnizations(orgnization));
        verify(orgnizationRepo, never()).save(any());
    }


    @Test
    void testDeleteOrgnizationSuccess() {
        
        String orgnizationId = "org123";
        Orgnizations orgnization = new Orgnizations();
        when(orgnizationRepo.findById(orgnizationId)).thenReturn(Optional.of(orgnization));

        String result = orgnizationService.deleteorgnization(orgnizationId);

        assertEquals("deleted succesfully", result);
        verify(orgnizationRepo, times(1)).delete(orgnization);
    }




}
