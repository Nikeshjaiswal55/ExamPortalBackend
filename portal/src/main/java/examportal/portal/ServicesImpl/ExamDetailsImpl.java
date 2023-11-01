package examportal.portal.ServicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import examportal.portal.Entity.ExamDetails;
import examportal.portal.Repo.ExamDetailsRepo;
import examportal.portal.Services.ExamDetailsService;
@Service
public class ExamDetailsImpl implements ExamDetailsService {

    @Autowired
    private ExamDetailsRepo examDetailsRepo;

    @Override
    public ExamDetails createExamDetails(ExamDetails examDetails) {
      ExamDetails neweExamDetails = this.examDetailsRepo.save(examDetails);
      return neweExamDetails;
    }
    
}
