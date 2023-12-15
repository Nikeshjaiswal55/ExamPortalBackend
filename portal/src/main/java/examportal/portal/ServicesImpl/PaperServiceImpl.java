package examportal.portal.ServicesImpl;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import examportal.portal.Payloads.PaginationDto;
import examportal.portal.Payloads.PaperDto;
import examportal.portal.Payloads.PaperStringDto;
import examportal.portal.Repo.AssessmentRepo;
import examportal.portal.Repo.AttemptepaperRepo;
import examportal.portal.Repo.ExamDetailsRepo;
import examportal.portal.Repo.InvitationRepo;
import examportal.portal.Repo.PaperRepo;
import examportal.portal.Repo.QuestionsRepo;
import examportal.portal.Repo.StudentRepo;
import examportal.portal.Repo.UserRepo;
import examportal.portal.Response.PaperResponce;
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

  Logger log = LoggerFactory.getLogger("PaperServiceImpl");

  @Override
  public Paper createPaper(PaperDto paperDto) {
    log.info("paperServiceIml Createpaper method Starts :");

    LocalDateTime date = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    String formattedDate = date.format(formatter);

    Paper paper = paperDto.getPaper();
    paper.setCreated_date(formattedDate);
    paper.setIs_Active("false");
    paper.set_setup(true);
    Paper newPaper = this.paperRepo.save(paper);

    List<Questions> questionsList = paperDto.getQuestions();
    CompletableFuture<List<Questions>> saveQuestionsFuture = saveQuestionsAsync(questionsList, newPaper.getPaperId());

    ExamDetails examDetails = paperDto.getExamDetails();
    examDetails.setCreated_date(formattedDate);
    examDetails.setDescription(newPaper.getDescription());
    examDetails.setIs_Active("false");
    examDetails.set_Setup(true);
    examDetails.set_shorted(newPaper.is_shorted());
    examDetails.setPaperId(newPaper.getPaperId());
    this.examDetailsRepo.save(examDetails);
    System.out.println(examDetails + "kger  =============================================================");

    try {
      // Get the result of the asynchronous saveQuestionsAsync call
      List<Questions> savedQuestions = saveQuestionsFuture.get();
      log.info("Questions saved asynchronously: {}", savedQuestions);
    } catch (Exception e) {
      log.error("Error saving questions asynchronously: {}", e.getMessage());
      // Handle the exception
    }

    log.info("paperServiceIml Create paper method End's :");
    return newPaper;
  }

  @Async
  public CompletableFuture<List<Questions>> saveQuestionsAsync(List<Questions> questionsList, String paperId) {
    // Set paperId for each question
    log.info("paperServiceIml saveQuestionsAsync method Starts ");
    questionsList.forEach(question -> question.setPaperID(paperId));

    // Save all questions in a batch asynchronously
    List<Questions> savedQuestions = this.questionsRepo.saveAll(questionsList);
    log.info("paperServiceIml saveQuestionsAsync method End ");
    return CompletableFuture.completedFuture(savedQuestions);
  }

  @Override
  public List<PaperDto> getAllPaper(Integer pageNumber, Integer size, String sortField, String sortOrder) {
    log.info("paperServiceIml getAllPaper method Starts ");
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
    log.info("paperServiceIml getAllPaper method End");
    return dto;
  }

  @Override
  public PaperStringDto getPaperById(String paperID) {
    log.info("paperServiceIml getPaperById method Starts :");
    Paper paper = this.paperRepo.findById(paperID)
        .orElseThrow(() -> new ResourceNotFoundException("paper", "paperID", paperID));
    PaperDto paperDto = this.mapper.map(paper, PaperDto.class);
    List<Questions> qList = this.questionsRepo.getAllQuestionsByPaperId(paperID);
    ExamDetails examDetails = this.examDetailsRepo.getExamDetailsByPaperID(paperID);

    paperDto.setQuestions(qList);
    paperDto.setExamDetails(examDetails);

    String obj = encodeObject(paperDto);
    PaperStringDto dto = new PaperStringDto();
    dto.setData(obj);
    log.info("paperServiceIml getPaperByID method End's :");

    return dto;
  }

  public String encodeObject(Object object) {
    log.info("paperServiceIml encodeObject method Starts ");
    ObjectMapper objectMapper = new ObjectMapper();

    try {
      String jsonString = objectMapper.writeValueAsString(object);
      String encodedString = URLEncoder.encode(jsonString, StandardCharsets.UTF_8);
      return encodedString;
    } catch (JsonProcessingException e) {
      // Handle the exception, e.g., log or throw a custom exception
      e.printStackTrace();
      log.info("paperServiceIml encodeObject method End ");
      return null;
    }
  }

  public Object decodeObject(String encodedString) {
    log.info("paperServiceIml decodeObject method Starts ");
    ObjectMapper objectMapper = new ObjectMapper();

    try {
      String decodedString = UriUtils.decode(encodedString, StandardCharsets.UTF_8);
      Object decodedObject = objectMapper.readValue(decodedString, Object.class);
      return decodedObject;
    } catch (JsonProcessingException e) {
      // Handle the exception, e.g., log or throw a custom exception
      e.printStackTrace();
      log.info("paperServiceIml decodeObject method End ");
      return null;
    }
  }

  @Override
  public PaperDto updetPaper(PaperDto paperDto) {

    log.info("PaperSerivceImp Update paper method Starts :");

    Paper paper = this.paperRepo.findById(paperDto.getPaperId())
        .orElseThrow(() -> new ResourceNotFoundException("paper", "paperId", paperDto.getPaperId()));
    paper = paperDto.getPaper();
     Paper npaper =this.paperRepo.save(paper);
    PaperDto dto = new PaperDto();

    List<Questions> q2 = new ArrayList<>();

    for (Questions ans : paperDto.getQuestions()) {
      Questions upadateqQuestions = this.questionsRepo.findById(ans.getQuestionId())
          .orElseThrow(() -> new ResourceNotFoundException("Question", "QuestionID", ans.getQuestionId()));

      upadateqQuestions.setCorrectAns(ans.getCorrectAns());
      upadateqQuestions.setPaperID(ans.getPaperID());
      upadateqQuestions.setOptions(ans.getOptions());
      upadateqQuestions.setQuestions(ans.getQuestions());

      Questions update = this.questionsRepo.save(upadateqQuestions);

      q2.add(update);

    }

    ExamDetails examDetails = this.examDetailsRepo.getExamDetailsByPaperID(paperDto.getPaperId());
    examDetails = paperDto.getExamDetails();
    ExamDetails updateExamDetails = this.examDetailsRepo.save(examDetails);

    dto.setPaper(npaper);
    dto.setQuestions(q2);
    dto.setExamDetails(updateExamDetails);

    log.info("paperService Update paper method End :");

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

  // With FIlter
  @Override
  public PaperResponce getAllPaperByUserId(String userId, PaginationDto dto, Map<String, String> filter) {
    log.info("paperServiceImpl getAllPaperByUserId  method Starts");

    Sort sort = (dto.getSortDirection().equalsIgnoreCase("ASC")) ? Sort.by(dto.getProperty()).ascending()
        : Sort.by(dto.getProperty()).descending();
    Pageable p = PageRequest.of(dto.getPageNo(), dto.getPageSize(), sort);

    Page<Paper> page = this.paperRepo.findByFiter(userId, p, filter);
    List<Paper> paper = page.getContent();
    List<ExamDetails> examDetails = new ArrayList<>();

    for (Paper paper2 : paper) {
      ExamDetails emd = new ExamDetails();
      emd = this.examDetailsRepo.getExamDetailsByPaperID(paper2.getPaperId());
      emd.setIs_Active(paper2.getIs_Active());
      emd.set_Setup(paper2.is_setup());
      examDetails.add(emd);
    }

    PaperResponce paperResponce = new PaperResponce();
    paperResponce.setCurrentPage(page.getNumber());
    paperResponce.setData(examDetails);
    paperResponce.setIslastPage(page.isLast());
    paperResponce.setPagesize(page.getSize());
    paperResponce.setTotalElements(page.getTotalElements());
    paperResponce.setTotalPages(page.getTotalPages());
    log.info("paperServiceImpl getAllPaperByUserId  method End");
    return paperResponce;
  }

  // Without Filter
  @Override
  public PaperResponce getAllPaperByUserIdWithOutFilter(String userId, PaginationDto dto) {
    log.info("paperServiceImpl getAllPaperByUserIdWithOutFilter  method Starts");

    Sort sort = (dto.getSortDirection().equalsIgnoreCase("ASC")) ? Sort.by(dto.getProperty()).ascending()
        : Sort.by(dto.getProperty()).descending();
    Pageable p = PageRequest.of(dto.getPageNo(), dto.getPageSize(), sort);

    List<ExamDetails> examDetails = new ArrayList<>();

    Page<Paper> page = this.paperRepo.findAllPaperByUserId(userId, p);

    List<Paper> paper = page.getContent();

    for (Paper paper2 : paper) {
      ExamDetails emd = new ExamDetails();
      emd = this.examDetailsRepo.getExamDetailsByPaperID(paper2.getPaperId());
      emd.setIs_Active(paper2.getIs_Active());
      emd.set_Setup(paper2.is_setup());
      examDetails.add(emd);

    }

    PaperResponce paperResponce = new PaperResponce();
    paperResponce.setCurrentPage(page.getNumber() + 1);
    paperResponce.setData(examDetails);
    paperResponce.setIslastPage(page.isLast());
    paperResponce.setPagesize(page.getSize());
    paperResponce.setTotalElements(page.getTotalElements());
    paperResponce.setTotalPages(page.getTotalPages());
    log.info("paperServiceImpl getAllPaperByUserIdWithOutFilter  method End");
    return paperResponce;
  }

  @Override
  public String activatePaper(String paperId, boolean active) {
    log.info("paperServiceImpl activatePaper  method Starts");
    String msg = "";

    LocalDateTime date = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    String formattedDate = date.format(formatter);

    Paper paper = this.paperRepo.findById(paperId)
        .orElseThrow(() -> new ResourceNotFoundException("Paper", "PaperId", paperId));
    ExamDetails examDetails = this.examDetailsRepo.getExamDetailsByPaperID(paperId);
    if (active == true) {
      paper.setIs_Active("false");
      paper.set_setup(true);
      examDetails.setIs_Active("false");
      examDetails.set_Setup(true);
      Paper ActivePaper = this.paperRepo.save(paper);
      this.examDetailsRepo.save(examDetails);
      log.info("paperServiceImpl activatePaper  method Ends");

      return msg = "Deactive successfully";

    } else {

      paper.setIs_Active("true");
      paper.set_setup(false);
      examDetails.setIs_Active("true");
      examDetails.set_Setup(false);
      Paper ActivePaper = this.paperRepo.save(paper);
      this.examDetailsRepo.save(examDetails);
      log.info("paperServiceImpl activatePaper  method Ends");
      return msg = "Paper Activated Successfully";

    }

  }

  public String decodeString(String encodedString) {
    log.info("paperServiceImpl decodeString  method Starts");
    ObjectMapper objectMapper = new ObjectMapper();

    try {
      String decodedString = UriUtils.decode(encodedString, StandardCharsets.UTF_8);
      return decodedString;
    } catch (Exception e) {
      // Handle the exception, e.g., log or throw a custom exception
      e.printStackTrace();
      log.info("paperServiceImpl decodeString  method End");
      return null;
    }
  }

  @Async
  public CompletableFuture<String> processInvitationsInBackground(String paperId) {
    log.info("paperServiceImpl processInvitationsInBackground  method Starts");
    ExamDetails examDetails = this.examDetailsRepo.getExamDetailsByPaperID(paperId);
    if (examDetails.getBranch() != null) {

      List<Student> students = this.studentRepo.getAllStudentBYBranchAndYear(examDetails.getBranch(),
          examDetails.getSession());

      students.forEach(student -> {
        User user = this.userRepo.findById(student.getStudentid())
            .orElseThrow(() -> new ResourceNotFoundException("user ", "userID", student.getStudentid()));
        // String password = decodeString(user.getPassword());
        String msg = user.getPassword();

        this.emailServiceImpl.sendFormateMail(user.getEmail(), msg, "login credentials", user.getRole());

      });
      return CompletableFuture.completedFuture("sending email in background");
    } else {

      List<InvitedStudents> students = this.invitationRepo.getAllStudentByPaperId(paperId);

      students.forEach(invitedStudents -> {
        User user = this.userRepo.findById(invitedStudents.getStudentId())
            .orElseThrow(() -> new ResourceNotFoundException("user ", "userID", invitedStudents.getStudentId()));

        String msg = "Password =>" + user.getPassword();

        this.emailServiceImpl.sendFormateMail(user.getEmail(), msg, "login credentials", user.getRole());
      });
      log.info("paperServiceImpl processInvitationsInBackground  method End");
      return CompletableFuture.completedFuture("sending email in background");
    }
  }

  @Override
  public List<ExamDetails> getAllAssessmentsByUserId(String userId) {
    log.info("paperServiceImpl getAllAssessmentsByUserId  method Starts");
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
    log.info("paperServiceImpl getAllAssessmentsByUserId  method End");
    return examDetails;
  }

  @Override
  public AttemptedPapers AttemptPaper(Assessment assessment) {
    log.info("paperServiceImpl AttemptPaper  method Start");
    AttemptedPapers attemptedPapers = new AttemptedPapers();
    attemptedPapers.setAssmentId(assessment.getAssessmentID());
    attemptedPapers.setPaperId(assessment.getPaperId());
    attemptedPapers.setStudentId(assessment.getUserId());

    AttemptedPapers save = this.attemptepaperRepo.save(attemptedPapers);
    log.info("paperServiceImpl AttemptPaper  method End");
    return attemptedPapers;
  }

  @Override
  public ExamDetails GetattemptedStudents(String paperId, String studentId) {
    log.info("paperServiceImpl GetattemptedStudents  method Start");
    AttemptedPapers attemptedPapers = this.attemptepaperRepo.getAllAttemptedPaperbyStudentID(studentId, paperId);
    Student student = this.studentRepo.findById(attemptedPapers.getStudentId())
        .orElseThrow(() -> new ResourceNotFoundException("Student", "StudentID", attemptedPapers.getStudentId()));
    ExamDetails examDetails = this.examDetailsRepo.getExamDetailsByPaperID(paperId);

    if (student != null) {
      examDetails.set_attempted(true);
    }
    log.info("paperServiceImpl GetattemptedStudents  method End");
    return examDetails;
  }

}