package examportal.portal.Services;
import java.util.List;

import examportal.portal.Entity.Mentor;
// import examportal.portal.Response.PostResponse;

public interface MentorService {
    
    //add Mentor
    Mentor addMentor(Mentor mentor);
    //get all Mentor
    List<Mentor> getAllMentors(Integer pageNumber,Integer pageSize,String sortBy ,String sortDir) ;
    //get mentor by id
    Mentor getMentorById(String mentorID);
    //update mentor info
    Mentor updateMentor(Mentor mentor);
    //delete mentor
    String deleteMentor(String id);
    // serch mentor by name
    List<Mentor> serchMentors(String mentorName);
    
}

