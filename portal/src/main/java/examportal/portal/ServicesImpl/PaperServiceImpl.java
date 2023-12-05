package examportal.portal.ServicesImpl;

import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import examportal.portal.Entity.Assessment;
import examportal.portal.Entity.AttemptedPapers;
import examportal.portal.Entity.ExamDetails;
import examportal.portal.Entity.InvitedStudents;
import examportal.portal.Entity.Paper;
import examportal.portal.Entity.Questions;
import examportal.portal.Entity.Student;
import examportal.portal.Entity.User;
import examportal.portal.Exceptions.ResourceNotFoundException;
import examportal.portal.Payloads.PaperDto;
import examportal.portal.Repo.AssessmentRepo;
import examportal.portal.Repo.AttemptepaperRepo;
import examportal.portal.Repo.ExamDetailsRepo;
import examportal.portal.Repo.InvitationRepo;
import examportal.portal.Repo.PaperRepo;
import examportal.portal.Repo.QuestionsRepo;
import examportal.portal.Repo.StudentRepo;
import examportal.portal.Repo.UserRepo;
import examportal.portal.Services.ExamDetailsService;
import examportal.portal.Services.PaperService;
@Service
public class PaperServiceImpl implements PaperService {

  @Autowired
  private PaperRepo paperRepo;

  @Autowired
  private StudentRepo studentRepo;

  @Autowired
  private ModelMapper mapper;

  @Autowired
  private QuestionsRepo questionsRepo;

  @Autowired
  private ExamDetailsRepo examDetailsRepo;

  @Autowired
  private InvitationRepo invitationRepo;

  @Autowired
  private EmailServiceImpl emailServiceImpl;

  @Autowired
  private UserRepo userRepo;

  @Autowired
  private AssessmentRepo assessmentRepo;

  @Autowired
  private AttemptepaperRepo attemptepaperRepo;

  @Autowired
  private ExamDetailsService examDetailsService;

  Logger log = LoggerFactory.getLogger("PaperServiceImpl");

  @Override
  public Paper createPaper(PaperDto paperdDto) {

    log.info("paperService Create paper method Starts :");

    Paper paper = new Paper();
    paper.setUserId(paperdDto.getUserId());
    paper.setOrgnizationId(paperdDto.getOrgnizationId());
    paper.set_setup(true);
    paper.set_Active(false);
    Paper newpPaper = this.paperRepo.save(paper);

    ExamDetails examDetails = this.examDetailsService.createExamDetails(paperdDto.getExamDetails());

    List<Questions> questionsList = paperdDto.getQuestions();

    this.questionsRepo.saveAll(questionsList);

    log.info("paperService Create paper method End's :");
    return newpPaper;
  }

  @Override
  public List<PaperDto> getAllPaper() {
    log.info("paperService getAll paper method Starts :");

    List<Paper> papers = this.paperRepo.findAll();

    List<PaperDto> paperDtos = new ArrayList<>();

    for (Paper paper : papers) {
      PaperDto newPaper = this.mapper.map(paper, PaperDto.class);
      paperDtos.add(newPaper);
    }
    List<PaperDto> dto = new ArrayList<>();
    for (PaperDto paper : paperDtos) {
      List<Questions> questions = this.questionsRepo.getAllQuestionsByPaperId(paper.getPaperId());
      ExamDetails examDetails = this.examDetailsRepo.getExamDetailsByPaperID(paper.getPaperId());
      paper.setQuestions(questions);
      paper.setExamDetails(examDetails);
      dto.add(paper);
    }
    log.info("paperService Create paper method End's :");
    return dto;
  }

  @Override
  public PaperDto getPaperById(String paperID) {
    log.info("paperService getPaperById method Starts :");
    Paper paper = this.paperRepo.findById(paperID)
        .orElseThrow(() -> new ResourceNotFoundException("paper", "paperID", paperID));
    PaperDto paperDto = this.mapper.map(paper, PaperDto.class);
    List<Questions> questions = this.questionsRepo.getAllQuestionsByPaperId(paperID);
    ExamDetails examDetails = this.examDetailsRepo.getExamDetailsByPaperID(paperID);

    paperDto.setQuestions(questions);
    paperDto.setExamDetails(examDetails);

    log.info("paperService getPaperByID method End's :");
    return paperDto;
  }

  @Override
  public PaperDto updetPaper(PaperDto paperDto) {

    log.info("paperService Update paper method Starts :");

    Paper paper = this.paperRepo.findById(paperDto.getPaperId())
        .orElseThrow(() -> new ResourceNotFoundException("paper", "paperId", paperDto.getPaperId()));

    PaperDto dto = this.mapper.map(paper, PaperDto.class);

    List<Questions> questions = paperDto.getQuestions();

    List<Questions> q2 = new ArrayList<>();

    for (Questions ans : questions) {
      Questions upadateqQuestions = this.questionsRepo.findById(ans.getQuestionId())
          .orElseThrow(() -> new ResourceNotFoundException("Question", "QuestionID", ans.getQuestionId()));

      upadateqQuestions.setUserAns(ans.getUserAns());

      Questions update = this.questionsRepo.save(upadateqQuestions);

      q2.add(update);

    }

    ExamDetails examDetails = this.examDetailsRepo.getExamDetailsByPaperID(paperDto.getPaperId());
    examDetails.setPaperId(paperDto.getPaperId());
    examDetails.setBranch(paperDto.getExamDetails().getBranch());
    examDetails.setExamDuration(paperDto.getExamDetails().getExamDuration());
    examDetails.setExamMode(paperDto.getExamDetails().getExamMode());
    examDetails.setExamRounds(paperDto.getExamDetails().getExamRounds());
    examDetails.setPaperChecked(false);
    examDetails.setSession(paperDto.getExamDetails().getSession());
    examDetails.setAssessmentName(paperDto.getExamDetails().getAssessmentName());
    examDetails.setTotalMarks(paperDto.getExamDetails().getTotalMarks());
    examDetails.setMinimum_marks(paperDto.getExamDetails().getMinimum_marks());

    ExamDetails updateExamDetails = this.examDetailsRepo.save(examDetails);

    dto.setQuestions(q2);

    dto.setExamDetails(updateExamDetails);

    log.info("paperService Update paper method Starts :");

    return dto;

  }

  @Override

  public String deletePaperByPaperId(String paperID) {
    log.info("paperServiceImpl deletePaperByPaperId  method Starts");
    Paper p = this.paperRepo.findById(paperID)
        .orElseThrow(() -> new ResourceNotFoundException("Paper", "paperId", paperID));
    ExamDetails examDetails = this.examDetailsRepo.getExamDetailsByPaperID(paperID);
    this.examDetailsRepo.deleteById(examDetails.getExamid());
    List<Questions> questions = this.questionsRepo.getAllQuestionsByPaperId(paperID);
    for (Questions question : questions) {
      Questions qu = this.questionsRepo.findById(question.getQuestionId())
          .orElseThrow(() -> new ResourceNotFoundException("Question", "QuestionID", question.getQuestionId()));
      this.questionsRepo.deleteById(question.getQuestionId());
    }
    this.paperRepo.deleteById(paperID);

    log.info("paperServiceImpl deletePaperByPaperId  method Ends");
    return "Deleted success fully";

  }

  @Override
  public List<ExamDetails> getAllPaperByUserId(String userId) {
    log.info("paperServiceImpl getAllPaperByUserId  method Starts");

    List<Paper> paper = this.paperRepo.findAllPaperByUserId(userId);
    List<ExamDetails> examDetails = new ArrayList<>();

    for (Paper paper2 : paper) {
      ExamDetails emd = new ExamDetails();
      emd = this.examDetailsRepo.getExamDetailsByPaperID(paper2.getPaperId());
      emd.set_Active(paper2.is_Active());
      emd.set_Setup(paper2.is_setup());
      examDetails.add(emd);

    }

    return examDetails;

  }

  @Override
  public String activatePaper(String paperId) {
    log.info("paperServiceImpl activatePaper  method Starts");
    Paper paper = this.paperRepo.findById(paperId)
        .orElseThrow(() -> new ResourceNotFoundException("Paper", "PaperId", paperId));
    paper.set_Active(true);
    paper.set_setup(false);
    Paper ActivePaper = this.paperRepo.save(paper);

    List<InvitedStudents> students = this.invitationRepo.getAllStudentByPaperId(paperId);

    for (InvitedStudents invitedStudents : students) {
      Student student = this.studentRepo.findById(invitedStudents.getStudentId())
          .orElseThrow(() -> new ResourceNotFoundException("Student ", "StudentID", invitedStudents.getStudentId()));
      User user = this.userRepo.findById(invitedStudents.getStudentId())
          .orElseThrow(() -> new ResourceNotFoundException("user ", "userID", invitedStudents.getStudentId()));
      String msg = "Use_Name => " + student.getEmail() + "\nPassword => " + user.getPassword();

      this.emailServiceImpl.sendFormateMail(student.getEmail(), msg, "login crenditials", user.getRole());

    }

    log.info("paperServiceImpl activatePaper  method Ends");

    return "Paper Published Successfully";
  }

  @Override
  public List<ExamDetails> getAllAssessmentsByUserId(String userId) {

    List<Assessment> assment = this.assessmentRepo.getAssessmentsBy_userId(userId);
    List<ExamDetails> examDetails = new ArrayList<>();

    for (Assessment assessment : assment) {
      AttemptedPapers  attemptedPapers = this.attemptepaperRepo.getAllAttemptedPaperbyStudentID(userId, assessment.getPaperId());
      ExamDetails examDetail = this.examDetailsRepo.getExamDetailsByPaperID(assessment.getPaperId());
      if (attemptedPapers!=null) {
        examDetail.set_attempted(true);
      }
      examDetails.add(examDetail);
    }

    return examDetails;
  }

  @Override
  public AttemptedPapers AttemptPaper(Assessment assessment) {
    AttemptedPapers attemptedPapers = new AttemptedPapers();
    attemptedPapers.setAssmentId(assessment.getAssessmentID());
    attemptedPapers.setPaperId(assessment.getPaperId());
    attemptedPapers.setStudentId(assessment.getUserId());

    AttemptedPapers save = this.attemptepaperRepo.save(attemptedPapers);

    return attemptedPapers;
  }

  @Override
  public ExamDetails GetattemptedStudents(String paperId) {
    AttemptedPapers attemptedPapers = this.attemptepaperRepo.GetattemptedStudentsByPaperId(paperId);
    Student student = this.studentRepo.findById(attemptedPapers.getStudentId())
        .orElseThrow(() -> new ResourceNotFoundException("Student", "StudentID", attemptedPapers.getStudentId()));
    ExamDetails examDetails = this.examDetailsRepo.getExamDetailsByPaperID(paperId);

    if (student != null) {
      examDetails.set_attempted(true);
    }
    return examDetails;
  }
}