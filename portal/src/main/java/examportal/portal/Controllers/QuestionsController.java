package examportal.portal.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import examportal.portal.Entity.Questions;
import examportal.portal.Services.QuestionService;


@RestController
public class QuestionsController {

    @Autowired
    private QuestionService questionService;

    @PostMapping("/create/Question")
    public ResponseEntity<Questions> createQuestion(@RequestBody Questions questions)
    {
        Questions questions2 = this.questionService.createQuestions(questions);
        return new ResponseEntity<>(questions2,HttpStatus.CREATED);
    }    

    @PutMapping("/updateQuestions")
    public ResponseEntity<Questions> updateQuestion(@RequestBody Questions questions)
    {
        Questions questions2 = this.questionService.updateQuestions(questions);
        return new ResponseEntity<>(questions2,HttpStatus.ACCEPTED);
    }

    @GetMapping("/getQuestionByID/{questionId}")
    public ResponseEntity<Questions> getQuestionByID(@PathVariable String questionId)
    {
        Questions questions = this.questionService.getQuestionsByID(questionId);

        return new ResponseEntity<>(questions,HttpStatus.OK);
    }

     @GetMapping("/getall/questions/{paperId}")
    public ResponseEntity<List<Questions>> getall(@PathVariable String paperId)
    {
        System.out.println("enter..............");
        List<Questions> questions = this.questionService.getAllQuestionsById(paperId);
        System.out.println("out..................");

        return new ResponseEntity<>(questions,HttpStatus.OK);
    }

    @DeleteMapping("/DeleteQuestion/{QuestionID}")
    public ResponseEntity<String> deleteQuestions(@PathVariable String QuestionID)
    {
        this.questionService.deleteQuestion(QuestionID);
        return new ResponseEntity<>("deleted Successfully",HttpStatus.OK);
    }

}
