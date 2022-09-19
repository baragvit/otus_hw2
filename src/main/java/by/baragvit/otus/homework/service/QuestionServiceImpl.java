package by.baragvit.otus.homework.service;

import by.baragvit.otus.homework.dao.QuestionDao;
import by.baragvit.otus.homework.model.Question;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
  private final QuestionDao questionDao;

  public QuestionServiceImpl(QuestionDao questionDao) {
    this.questionDao = questionDao;
  }

  @Override
  public List<Question> getQuestions() {
    return questionDao.getQuestions();
  }
}
