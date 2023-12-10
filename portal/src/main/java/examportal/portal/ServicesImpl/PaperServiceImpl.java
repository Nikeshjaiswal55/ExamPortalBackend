package examportal.portal.ServicesImpl;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.CompletableFuture;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
// import examportal.portal.Services.ExamDetailsService;
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

  // @Autowired
  // private ExamDetailsService examDetailsService;

  Logger log = LoggerFactory.getLogger("PaperServiceImpl");

  @Override
  public Paper createPaper(PaperDto paperDto) {
      log.info("paperService Create paper method Starts :");

      Paper paper = new Paper();
      paper.setUserId(paperDto.getUserId());
      paper.setOrgnizationId(paperDto.getOrgnizationId());
      paper.set_setup(true);
      paper.set_Active(false);
      Paper newPaper = this.paperRepo.save(paper);

      ExamDetails examDetails = paperDto.getExamDetails(); 
      examDetails.setPaperId(newPaper.getPaperId());
      this.examDetailsRepo.save(examDetails);
      System.out.println(examDetails+"kge ==================================================================");

      List<Questions> questionsList = paperDto.getQuestions();

      // Save questions asynchronously
      CompletableFuture<List<Questions>> saveQuestionsFuture = saveQuestionsAsync(questionsList, newPaper.getPaperId());

      // Perform other synchronous operations while waiting for saveQuestionsAsync to complete...

      // try {
      //     // Get the result of the asynchronous saveQuestionsAsync call
      //     List<Questions> savedQuestions = saveQuestionsFuture.get();
      //     log.info("Questions saved asynchronously: {}", savedQuestions);
      // } catch (Exception e) {
      //     log.error("Error saving questions asynchronously: {}", e.getMessage());
      //     // Handle the exception
      // }

      log.info("paperService Create paper method End's :");
      return newPaper;
  }

@Async
public CompletableFuture<List<Questions>> saveQuestionsAsync(List<Questions> questionsList, String paperId) {
    // Set paperId for each question
    questionsList.forEach(question -> question.setPaperID(paperId));
    // Save all questions in a batch asynchronously
    List<Questions> savedQuestions = this.questionsRepo.saveAll(questionsList);

    return CompletableFuture.completedFuture(savedQuestions);
}


  @Override
  public List<PaperDto> getAllPaper(Integer pageNumber, Integer size, String sortField, String sortOrder) {
    log.info("paperService getAll paper method Starts :");
    Sort sort = (sortOrder.equalsIgnoreCase("ASC")) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
    Pageable p = PageRequest.of(pageNumber, size, sort);
    Page<Paper> pp = paperRepo.findAll(p);
    List<Paper> papers = pp.getContent();

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
  public String getPaperById(String paperID) {
    log.info("paperService getPaperById method Starts :");
    Paper paper = this.paperRepo.findById(paperID)
        .orElseThrow(() -> new ResourceNotFoundException("paper", "paperID", paperID));
    PaperDto paperDto = this.mapper.map(paper, PaperDto.class);
    List<Questions> qList = this.questionsRepo.getAllQuestionsByPaperId(paperID);
    ExamDetails examDetails = this.examDetailsRepo.getExamDetailsByPaperID(paperID);

    paperDto.setQuestions(qList);
    paperDto.setExamDetails(examDetails);

    String obj = encodeObject(paperDto);

    log.info("paperService getPaperByID method End's :");

    return obj;
  }

  public String encodeObject(Object object) {
    ObjectMapper objectMapper = new ObjectMapper();

    try {
      String jsonString = objectMapper.writeValueAsString(object);
      String encodedString = URLEncoder.encode(jsonString, StandardCharsets.UTF_8);
      return encodedString;
    } catch (JsonProcessingException e) {
      // Handle the exception, e.g., log or throw a custom exception
      e.printStackTrace();
      return null;
    }
  }

  public Object decodeObject(String encodedString) {
    ObjectMapper objectMapper = new ObjectMapper();

    try {
      String decodedString = UriUtils.decode(encodedString, StandardCharsets.UTF_8);
      Object decodedObject = objectMapper.readValue(decodedString, Object.class);
      return decodedObject;
    } catch (JsonProcessingException e) {
      // Handle the exception, e.g., log or throw a custom exception
      e.printStackTrace();
      return null;
    }
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
    ExamDetails examDetails = this.examDetailsRepo.getExamDetailsByPaperID(paperID);
    this.examDetailsRepo.deleteById(examDetails.getExamid());
    List<Questions> questions = this.questionsRepo.getAllQuestionsByPaperId(paperID);
    this.questionsRepo.deleteAll(questions);
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
  public String activatePaper(String paperId, boolean active) {
    log.info("paperServiceImpl activatePaper  method Starts");

    Paper paper = this.paperRepo.findById(paperId)
        .orElseThrow(() -> new ResourceNotFoundException("Paper", "PaperId", paperId));
    ExamDetails examDetails = this.examDetailsRepo.getExamDetailsByPaperID(paperId);
    if (active == true) {
      paper.set_Active(false);
      paper.set_setup(true);
      examDetails.set_Active(false);
      examDetails.set_Setup(true);
      Paper ActivePaper = this.paperRepo.save(paper);
      this.examDetailsRepo.save(examDetails);
      return "Deactive successfully";
    } else {
      paper.set_Active(true);
      paper.set_setup(false);
      examDetails.set_Active(true);
      examDetails.set_Setup(false);
      Paper ActivePaper = this.paperRepo.save(paper);
      this.examDetailsRepo.save(examDetails);
    }

    log.info("paperServiceImpl activatePaper  method Ends");

    return "Paper Published Successfully";
  }

 
  @Async
  public CompletableFuture<String> processInvitationsInBackground(String paperId) {
      ExamDetails examDetails = this.examDetailsRepo.getExamDetailsByPaperID(paperId);

      if (examDetails.getBranch() != null) {
          List<Student> students = this.studentRepo.getAllStudentBYBranch(examDetails.getBranch());
          for (Student student : students) {
              sendEmailAsync(student.getStudentid());
          }
      } else {
          List<InvitedStudents> students = this.invitationRepo.getAllStudentByPaperId(paperId);
          if (students==null) {
            throw new ResourceNotFoundException("Students","branch",examDetails.getBranch());
          }
          else
          {
          for (InvitedStudents invitedStudents : students) {
              sendEmailAsync(invitedStudents.getStudentId());
          }}
      }

      return CompletableFuture.completedFuture("Sending email in the background");
  }

  @Async
  public CompletableFuture<String> sendEmailAsync(String userId) {
      User user = this.userRepo.findById(userId)
              .orElseThrow(() -> new ResourceNotFoundException("user", "userID", userId));

      String msg = "User_Name => " + user.getEmail() + "    Password =>" + user.getPassword();
      System.out.println("Sending email asynchronously for user: " + user.getEmail());

      this.emailServiceImpl.sendFormateMail(user.getEmail(), msg, "login credentials", user.getRole());
      return CompletableFuture.completedFuture("Sendig email in background");
  }

  @Override
  public List<ExamDetails> getAllAssessmentsByUserId(String userId) {

    List<Assessment> assment = this.assessmentRepo.getAssessmentsBy_userId(userId);
    List<ExamDetails> examDetails = new ArrayList<>();

    for (Assessment assessment : assment) {
      AttemptedPapers attemptedPapers = this.attemptepaperRepo.getAllAttemptedPaperbyStudentID(userId,
          assessment.getPaperId());
      ExamDetails examDetail = this.examDetailsRepo.getExamDetailsByPaperID(assessment.getPaperId());
      if (attemptedPapers != null) {
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
  public ExamDetails GetattemptedStudents(String paperId, String studentId) {
    AttemptedPapers attemptedPapers = this.attemptepaperRepo.getAllAttemptedPaperbyStudentID(studentId, paperId);
    Student student = this.studentRepo.findById(attemptedPapers.getStudentId())
        .orElseThrow(() -> new ResourceNotFoundException("Student", "StudentID", attemptedPapers.getStudentId()));
    ExamDetails examDetails = this.examDetailsRepo.getExamDetailsByPaperID(paperId);

    if (student != null) {
      examDetails.set_attempted(true);
    }
    return examDetails;
  }

  @Override
  public List<Paper> getAllpaperByName(String name) {
    List<Paper> pprName = paperRepo.getAllpaperByName(name);
    if (pprName.isEmpty()) {
      throw new NoSuchElementException("The Paper list is empty");
    }
    return pprName;
  }
}