package examportal.portal.ServicesImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import examportal.portal.Entity.ExamDetails;
import examportal.portal.Repo.ExamDetailsRepo;
import examportal.portal.Services.ExamDetailsService;
@Service
public class ExamDetailsImpl implements ExamDetailsService {

    @Autowired
    private ExamDetailsRepo examDetailsRepo;

     Logger log = LoggerFactory.getLogger("ExamDetailsImpl");

    @Override
    public ExamDetails createExamDetails(ExamDetails examDetails) {
      
      log.info("ExamDetails CreateExamDetails method Starts ");
      ExamDetails neweExamDetails = this.examDetailsRepo.save(examDetails);
      log.info("ExamDetails CreateExamDetails method Ends ");
      return neweExamDetails;
    }
    
}
