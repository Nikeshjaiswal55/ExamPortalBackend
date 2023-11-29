package examportal.portal.ServicesImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import examportal.portal.Entity.AttemptedQuestions;
import examportal.portal.Repo.AttemptedQuestionsRepo;
import examportal.portal.Services.AttemptedQuestionService;

@Service
public class AttemptedQuestionImpl implements AttemptedQuestionService {

    Logger log  = LoggerFactory.getLogger("AttemptedQuestionImpl.class");
    @Autowired
    private AttemptedQuestionsRepo attemptedQuestionsRepo;


    @Override
    public AttemptedQuestions createAttemptedQuestions(AttemptedQuestions attemptedQuestions) {

        log.info("AttemptedQuestionImpl , createAttemptedQuestions Started");

        AttemptedQuestions questions = this.attemptedQuestionsRepo.save(attemptedQuestions);
        
        log.info("AttemptedQuestionImpl , createAttemptedQuestions Ends");
        return questions;

    }
    
}
