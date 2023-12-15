// package examportal.portal;

// import org.springframework.boot.test.context.SpringBootTest;
//  import static org.mockito.ArgumentMatchers.eq;

// import examportal.portal.Entity.Course;
// import examportal.portal.Entity.Mentor;
// import examportal.portal.Payloads.EmailDetails;
// import examportal.portal.Repo.MentorRepo;
// import examportal.portal.Services.EmailService;
// import examportal.portal.Services.MentorService;
// import examportal.portal.ServicesImpl.MentorSerivceImpl;
// import jakarta.inject.Inject;

// import static org.hamcrest.Matchers.containsInAnyOrder;
// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.ArgumentMatchers.contains;
// import static org.mockito.Mockito.times;
// import static org.mockito.Mockito.verify;
// import static org.mockito.Mockito.when;
// import java.util.List;
// import java.util.Optional;

// import org.junit.jupiter.api.Test;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.Mockito;
// import org.springframework.boot.test.context.SpringBootTest;

// //  @SpringBootTest(classes = MentorSericeImplTest.TestConfig.class)
// @SpringBootTest(classes = MentorSericeImplTest.class)
// public class MentorSericeImplTest {

//     // public class MockEmailService implements EmailService {
//     //     // Implement the methods of the interface with mock behavior
//     // }
    

//      @Mock
//      private EmailService emailService;
//     // @Mock
//     // private MockEmailService emailService;


//     @Mock
//     private MentorRepo mentorRepo;

//     @InjectMocks
//     private MentorSerivceImpl mentorSerivceImpl;


//      @Test
//     void testAddMentor(){
//          //Arrange
//         Mentor mentor=new Mentor("1","john Doe","John@example.com","org123","user123");
//         when(mentorRepo.save(any(Mentor.class))).thenReturn(mentor);

//         Mentor result = mentorSerivceImpl.addMentor(mentor);


//         assertEquals(mentor, result);
//         assertEquals(mentor, result);


       
//         verify(mentorRepo, Mockito.times(1)).save(any(Mentor.class));
//         // verify(emailService,Mockito.times(1)).sendSimpleMail(any(EmailDetails.class));

//     }

//     @Test
//     void testGetAllMentors(){

//           Mentor mentor1= new Mentor("1","Ansh","Ansh@exam","org456","user456");
//           Mentor mentor2 =new Mentor("2","Ansh","Ansh@example.com","org456","user456");

//           List<Mentor> mockMentors= java.util.Arrays.asList(mentor1,mentor2);

//           when(mentorRepo.findAll()).thenReturn(mockMentors);

//           //actual
//           List<Mentor>result=mentorSerivceImpl.getAllMentors();

//           //assert
//           assertEquals(2, result.size());
//          // assertEquals(result,containsInAnyOrder(mockMentors.toArray()));

//     }
         

//           @Test
//           public void testGetMentorById() {
//               // Arrange
//               Mentor mentor = new Mentor();
//               mentor.setMentorId("1");
      
//               // Act
//               when(mentorRepo.findById("1")).thenReturn(Optional.of(mentor));
//               Mentor retrievedMentor = mentorSerivceImpl.getMentorById("1");
      
//               // Assert
//               assertEquals("1", retrievedMentor.getMentorId());
      
//               // Verify that the repository findById method was called
//               verify(mentorRepo, times(1)).findById("1");

//            }
//      @Test
//     void testUpdateMentor(){
//         // arrnage

//         Mentor existingMentor=new Mentor("1","Ansh","user123","org123","Ansh@example.com");
//         Mentor updateMentor =new Mentor("1","Ansh","user456","org456","Ansh@example.com");

//         when(mentorRepo.findById("1")).thenReturn(Optional.of(existingMentor));
//         // when(mentorRepo.save(any(Mentor.class))).thenAnswer(null)(invocation ->{
//             // when(mentorRepo.save(any(Mentor.class))).thenReturn(updateMentor);

 
            
        
   

//         //actual
//         Mentor result = mentorSerivceImpl.updateMentor(updateMentor);


//         //assert
//         assertEquals("Ansh", result.getMentorName());
//         assertEquals("Ansh@example.com", result.getEmail());

//         // verify that the repository findById and save method ware called

//         verify(mentorRepo,times(1)).findById("1");
//         // verify(mentorRepo,times(1)).save(any(Mentor.class));

//     }


//     @Test
//     void testDeleteMentor() {
//         // Arrange
//         // Mentor mentor = new Mentor("1", "John Doe", "user123", "org123", "Ansh@example.com");
//         Mentor mentor=new Mentor("1","john doe","user123","org123","Ansh@example.com");

//         when(mentorRepo.findById("1")).thenReturn(Optional.of(mentor));

//         // Act
//         mentorSerivceImpl.deleteMentor("1");

//         // Verify that the repository findById and delete methods were called
//         verify(mentorRepo, times(1)).findById("1");
//         // verify(mentorRepo, times(1)).delete(eq(mentor));
//     }

    
// }
