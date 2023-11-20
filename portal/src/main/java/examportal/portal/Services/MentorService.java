package examportal.portal.Services;

import java.util.List;

import examportal.portal.Entity.Mentor;

public interface MentorService {
    
    //add Mentor
    Mentor addMentor(Mentor mentor);
    //get all Mentor
    List<Mentor>getAllMentors();
    //get mentor by id
    Mentor getMentorById(String mentorId);
    //update mentor info
    Mentor updateMentor(Mentor mentor);
    //delete mentor
    String deleteMentor(String id);
// get Mentor BY id
    
}

