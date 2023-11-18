package examportal.portal.ServicesImpl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import examportal.portal.Entity.ExamDetails;
import examportal.portal.Entity.Paper;
import examportal.portal.Entity.Questions;
import examportal.portal.Entity.Student;
import examportal.portal.Payloads.PaperDto;
import examportal.portal.Payloads.StudentDto;
import examportal.portal.Repo.ExamDetailsRepo;
import examportal.portal.Repo.PaperRepo;
import examportal.portal.Repo.QuestionsRepo;
import examportal.portal.Services.PaperService;
import examportal.portal.Services.QuestionService;
import examportal.portal.Services.StudentSevices;
import jakarta.el.ELException;

@Service
public class PaperServiceImpl implements PaperService {

  @Autowired
  private PaperRepo paperRepo;

  @Autowired
  private QuestionService questionService;

  @Autowired
  private ModelMapper mapper;

  @Autowired
  private QuestionsRepo questionsRepo;

  @Autowired
  private StudentSevices sevices;
  @Autowired
  private ExamDetailsRepo examDetailsRepo;
  
  Logger log = LoggerFactory.getLogger("PaperServiceImpl");

  @Override
  public Paper createPaper(PaperDto paperdDto) {

    log.info("paperService Create paper method Starts :");

    Paper paper = new Paper();
    paper.setUserId(paperdDto.getUserId());
    Paper newpPaper = this.paperRepo.save(paper);
    
     ExamDetails examDetails = new ExamDetails();
     examDetails.setPaperId(newpPaper.getPaperId());
     examDetails.setBranch(paperdDto.getExamDetails().getBranch());
     examDetails.setExamDuration(paperdDto.getExamDetails().getExamDuration());
     examDetails.setExamMode(paperdDto.getExamDetails().getExamMode());
     examDetails.setExamRounds(paperdDto.getExamDetails().getExamRounds());
     examDetails.setPaperChecked(false);
     examDetails.setSession(paperdDto.getExamDetails().getSession());
     examDetails.setAssessmentName(paperdDto.getExamDetails().getAssessmentName());

     ExamDetails examDetails2= this.examDetailsRepo.save(examDetails);
    System.out.println("my paper Id ============"+ examDetails2.getPaperId());

    List<Questions> questionsList = paperdDto.getQuestions();

    for (Questions questions : questionsList) {
      questions.setPaperID(newpPaper.getPaperId());
      this.questionService.createQuestions(questions);
    }
    StudentDto dto =new StudentDto();
    dto.setEmail(paperdDto.getEmails());
    dto.setToken(paperdDto.getToken());
    dto.setPaperID(newpPaper.getPaperId());

    Student student = this.sevices.addStudent(dto);
    System.out.println(student);

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
      ExamDetails examDetails = this.examDetailsRepo.getexExamDetailsByPaperID(paper.getPaperId());
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
    Paper paper = this.paperRepo.findById(paperID).orElseThrow(()-> new ELException("Paper not found"));
    PaperDto paperDto = this.mapper.map(paper, PaperDto.class);
    List<Questions> questions = this.questionsRepo.getAllQuestionsByPaperId(paperID);
    ExamDetails examDetails = this.examDetailsRepo.getexExamDetailsByPaperID(paperID);
    
      paperDto.setQuestions(questions);
      paperDto.setExamDetails(examDetails);
    
      log.info("paperService getPaperByID method End's :");
    return paperDto;
  }

  @Override
  public PaperDto updetPaper(PaperDto paperDto) {

    log.info("paperService Update paper method Starts :");

    Paper paper = this.paperRepo.findById(paperDto.getPaperId()).orElseThrow(()-> new ELException("Paper not found"));

    PaperDto dto = this.mapper.map(paper, PaperDto.class);

    List<Questions> questions = paperDto.getQuestions();

      List<Questions> q2 = new ArrayList<>();

      for (Questions ans : questions) {
        Questions upadateqQuestions = this.questionsRepo.findById(ans.getQuestionId()).orElseThrow(()-> new ELException("Question Not found"));

        upadateqQuestions.setUserAns(ans.getUserAns());

        Questions update= this.questionsRepo.save(upadateqQuestions);

        q2.add(update);

      }

      ExamDetails examDetails = this.examDetailsRepo.getexExamDetailsByPaperID(paperDto.getPaperId());
        examDetails.setPaperId(paperDto.getPaperId());
        examDetails.setBranch(paperDto.getExamDetails().getBranch());
        examDetails.setExamDuration(paperDto.getExamDetails().getExamDuration());
        examDetails.setExamMode(paperDto.getExamDetails().getExamMode());
        examDetails.setExamRounds(paperDto.getExamDetails().getExamRounds());
        examDetails.setPaperChecked(false);
        examDetails.setSession(paperDto.getExamDetails().getSession());
        examDetails.setAssessmentName(paperDto.getExamDetails().getAssessmentName());
      
      ExamDetails updateExamDetails = this.examDetailsRepo.save(examDetails);

     dto.setQuestions(q2);
     
     dto.setExamDetails(updateExamDetails);
      
     log.info("paperService Update paper method Starts :");

      return  dto;

  }

}
