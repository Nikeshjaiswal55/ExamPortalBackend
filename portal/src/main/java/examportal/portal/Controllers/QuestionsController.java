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
    // Add question
    @PostMapping("Question/create")
    public ResponseEntity<Questions> createQuestion(@RequestBody Questions questions)
    {
        Questions questions2 = this.questionService.createQuestions(questions);
        return new ResponseEntity<>(questions2,HttpStatus.CREATED);
    }    

    // Update a question
    @PutMapping("Questions/update")
    public ResponseEntity<Questions> updateQuestion(@RequestBody Questions questions)
    {
        Questions questions2 = this.questionService.updateQuestions(questions);
        return new ResponseEntity<>(questions2,HttpStatus.ACCEPTED);
    }
    //get question by qId
    @GetMapping("Question/getBy/{questionId}")
    public ResponseEntity<Questions> getQuestionByID(@PathVariable String questionId)
    {
        Questions questions = this.questionService.getQuestionsByID(questionId);

        return new ResponseEntity<>(questions,HttpStatus.OK);
    }
    
    // getting all question from a paper
    @GetMapping("Question/getAll/{paperId}")
    public ResponseEntity<List<Questions>> getall(@PathVariable String paperId)
    {
        System.out.println("hello==================================================================1");
        List<Questions> questions = this.questionService.getAllQuestion(paperId);

        return new ResponseEntity<List<Questions>>(questions,HttpStatus.OK);
    }

    //delete a question
    @DeleteMapping("Question/delete/{QuestionID}")
    public ResponseEntity<String> deleteQuestions(@PathVariable String QuestionID)
    {
        this.questionService.deleteQuestion(QuestionID);
        return new ResponseEntity<>("deleted Successfully",HttpStatus.OK);
    }

}
