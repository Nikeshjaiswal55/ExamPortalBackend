package examportal.portal.ServicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import examportal.portal.Entity.AttemptedQuestions;
import examportal.portal.Repo.AttemptedQuestionsRepo;
import examportal.portal.Services.AttemptedQuestionService;

@Service
public class AttemptedQuestionImpl implements AttemptedQuestionService {

    @Autowired
    private AttemptedQuestionsRepo attemptedQuestionsRepo;


    @Override
    public AttemptedQuestions createAttemptedQuestions(AttemptedQuestions attemptedQuestions) {

        AttemptedQuestions questions = this.attemptedQuestionsRepo.save(attemptedQuestions);

        return questions;

    }
    
}
