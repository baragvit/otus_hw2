package by.baragvit.otus.homework.service;

import by.baragvit.otus.homework.dao.QuestionDao;
import by.baragvit.otus.homework.model.Question;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class QuestionServiceTest {
  @InjectMocks
  private QuestionServiceImpl questionService;
  @Mock
  private QuestionDao questionDao;

  @Test
  public void getSimpleQuestionList() {
    List<Question> preparedQuestions = List.of(new Question("Question", "Answer"));
    when(questionDao.getQuestions()).thenReturn(preparedQuestions);

    List<Question> questions = questionService.getQuestions();

    assertAll(
        () -> assertNotNull(questions),
        () -> assertEquals(preparedQuestions, questions),
        () -> verify(questionDao).getQuestions()
    );
  }
}
