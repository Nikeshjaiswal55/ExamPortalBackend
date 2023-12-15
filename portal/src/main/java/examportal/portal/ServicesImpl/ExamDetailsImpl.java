package examportal.portal.ServicesImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import examportal.portal.Entity.ExamDetails;
import examportal.portal.Exceptions.ResourceNotFoundException;
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

  @Override
  public ExamDetails updateExamDetails(ExamDetails examDetails) {
    ExamDetails details = this.examDetailsRepo.findById(examDetails.getExamid())
        .orElseThrow(() -> new ResourceNotFoundException("ExamDetails", "ID", examDetails.getExamid()));
    ExamDetails examDetail = this.examDetailsRepo.getExamDetailsByPaperID(examDetails.getPaperId());
    examDetails.setBranch(examDetails.getBranch());
    examDetails.setAssessmentName(examDetails.getAssessmentName());
    examDetails.setCreated_date(examDetails.getCreated_date());
    examDetails.setDescription(examDetails.getDescription());
    examDetails.setExamDuration(examDetails.getExamDuration());
    examDetails.setExamMode(examDetails.getExamMode());
    examDetails.setExamRounds(examDetails.getExamRounds());
    examDetails.setInstruction(examDetails.getInstruction());
    examDetails.setMinimum_marks(examDetails.getMinimum_marks());
    examDetails.setPaperChecked(examDetails.isPaperChecked());
    // examDetails.setPaper_name(examDetails.getPaper_name());
    examDetails.setSession(examDetails.getSession());
    examDetails.setTotalMarks(examDetails.getTotalMarks());
    examDetails.set_Setup(examDetails.is_Setup());
    examDetails.set_attempted(examDetails.is_attempted());
    examDetails.set_auto_check(examDetails.is_auto_check());
    examDetails.set_shorted(examDetails.is_shorted());
    ExamDetails updateExamDetails = this.examDetailsRepo.save(examDetails);
    return updateExamDetails;
  }

}
