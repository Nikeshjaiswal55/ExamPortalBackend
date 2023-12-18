package examportal.portal.Controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import examportal.portal.Entity.ExamDetails;
import examportal.portal.Entity.Questions;
import examportal.portal.Services.ExamDetailsService;
import examportal.portal.Services.QuestionService;


@RestController
@CrossOrigin(origins = "*")
public class QuestionsController {

    Logger log = LoggerFactory.getLogger("QuestionsController");

    @Autowired
    private QuestionService questionService;

    @Autowired
    private ExamDetailsService examDetailsService;

    @PostMapping("/create/Question")
    public ResponseEntity<Questions> createQuestion(@RequestBody Questions questions)
    {   log.info("QuestionsController, createQuestion Method Start");
        Questions questions2 = this.questionService.createQuestions(questions);
           log.info("QuestionsController, createQuestion Method End");
        return new ResponseEntity<>(questions2,HttpStatus.CREATED);
    }    

    @PutMapping("/updateQuestions")
    public ResponseEntity<List<Questions>> updateQuestion(@RequestBody List<Questions> questions)
    {
          log.info("QuestionsController, updateQuestion Method Start");
        List<Questions> questions2 = this.questionService.updateQuestions(questions);
        log.info("QuestionsController, updateQuestion Method End");
        return new ResponseEntity<>(questions2,HttpStatus.ACCEPTED);
    }

    @GetMapping("/getQuestionByID/{questionId}")
    public ResponseEntity<Questions> getQuestionByID(@PathVariable String questionId)
    {   
        log.info("QuestionsController, getQuestionByID Method Start");
        Questions questions = this.questionService.getQuestionsByID(questionId);
        log.info("QuestionsController, getQuestionByID Method End");
        return new ResponseEntity<>(questions,HttpStatus.OK);
    }

     @GetMapping("/getall/questions/{paperId}")
    public ResponseEntity<List<Questions>> getall(@PathVariable String paperId)
    {  
         log.info("QuestionsController, getall Method Start");
        List<Questions> questions = this.questionService.getAllQuestionsById(paperId);
        log.info("QuestionsController, getall Method End");
        return new ResponseEntity<List<Questions>>(questions,HttpStatus.OK);
    }
    

    @DeleteMapping("/DeleteQuestion/{QuestionID}")
    public ResponseEntity<String> deleteQuestions(@PathVariable String QuestionID)
    {
         log.info("QuestionsController, deleteQuestions Method Start");
        this.questionService.deleteQuestion(QuestionID);
        log.info("QuestionsController, deleteQuestions Method Start");
        return new ResponseEntity<>("deleted Successfully",HttpStatus.OK);
    }

    @PutMapping("/updateExamdetails")
    public ResponseEntity<ExamDetails> updateExamdetails(@RequestBody ExamDetails examDetails)
    {
        ExamDetails exam = this.examDetailsService.updateExamDetails(examDetails);
        return new ResponseEntity<>(exam,HttpStatus.OK);
    }

}
