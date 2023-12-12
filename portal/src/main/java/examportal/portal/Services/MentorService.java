package examportal.portal.Services;

import java.util.List;

import examportal.portal.Entity.Mentor;

public interface MentorService {
    
    //add Mentor
    Mentor addMentor(Mentor mentor);
    //get all Mentor
    List<Mentor>getAllMentors(Integer pageNumber, int size, String sortField, String sortOrder);
    //get mentor by id
    Mentor getMentorById(String mentorID);
    //update mentor info
    Mentor updateMentor(Mentor mentor);
    //delete mentor
    String deleteMentor(String id);
    
   
    
}

