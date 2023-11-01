package examportal.portal.ServicesImpl;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import examportal.portal.Entity.ExamDetails;
import examportal.portal.Entity.Paper;
import examportal.portal.Entity.Questions;
import examportal.portal.Payloads.PaperDto;
import examportal.portal.Repo.ExamDetailsRepo;
import examportal.portal.Repo.PaperRepo;
import examportal.portal.Repo.QuestionsRepo;
import examportal.portal.Services.PaperService;
import examportal.portal.Services.QuestionService;
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
  private ExamDetailsRepo examDetailsRepo;
  

  @Override
  public Paper createPaper(PaperDto paperdDto) {

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
     ExamDetails examDetails2= this.examDetailsRepo.save(examDetails);
    System.out.println("my paper Id ============"+ examDetails2.getPaperId());

    List<Questions> questionsList = paperdDto.getQuestions();

    for (Questions questions : questionsList) {
      questions.setPaperID(newpPaper.getPaperId());
      this.questionService.createQuestions(questions);
    }

    return newpPaper;
  }

  @Override
  public List<PaperDto> getAllPaper() {
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
    return dto;
  }

  @Override
  public PaperDto getPaperById(String paperID) {
    Paper paper = this.paperRepo.findById(paperID).orElseThrow(()-> new ELException("Paper not found"));
    PaperDto paperDto = this.mapper.map(paper, PaperDto.class);
    List<Questions> questions = this.questionsRepo.getAllQuestionsByPaperId(paperID);
    ExamDetails examDetails = this.examDetailsRepo.getexExamDetailsByPaperID(paperID);
    
      paperDto.setQuestions(questions);
      paperDto.setExamDetails(examDetails);
    
    return paperDto;
  }

  @Override
  public PaperDto updetPaper(PaperDto paperDto) {
    Paper paper = this.paperRepo.findById(paperDto.getPaperId()).orElseThrow(()-> new ELException("Paper not found"));

    PaperDto dto = this.mapper.map(paper, PaperDto.class);

    // List<Questions> questions = this.questionsRepo.getAllQuestionsByPaperId(paperDto.getPaperId());

      // Questions question = this.questionsRepo.findById(questions2.getQuestionId()).orElseThrow(()-> new ELException("Question Not found"));

      List<Questions> question = paperDto.getQuestions();
      List<Questions> q2 = new ArrayList<>();

      for (Questions ans : question) {
        Questions qu = this.questionsRepo.findById(ans.getQuestionId()).orElseThrow(()-> new ELException("Question Not found"));

        qu.setUserAns(ans.getCorrectAns());

        this.questionsRepo.save(qu);

        

          q2.add(qu);
      }
      
      paperDto.setQuestions(q2);
    
    return  paperDto;

  }

}
