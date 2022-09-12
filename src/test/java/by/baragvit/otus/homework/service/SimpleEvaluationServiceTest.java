package by.baragvit.otus.homework.service;

import by.baragvit.otus.homework.model.Answer;
import by.baragvit.otus.homework.model.Question;
import by.baragvit.otus.homework.model.VerifiedAnswer;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SimpleEvaluationServiceTest {

  private EvaluationService evaluationService;

  @BeforeEach
  public void setUp() {
    evaluationService = new SimpleEvaluationService();
  }

  @Test
  public void testSuccessEvaluation() {
    List<Answer> answers = List.of(new Answer(new Question("question", "answer"), "answer"));
    evaluationService = new SimpleEvaluationService();

    List<VerifiedAnswer> verifiedAnswers = evaluationService.evaluate(answers);

    assertEquals(1, verifiedAnswers.size());
    assertTrue(verifiedAnswers.iterator().next().isCorrect());
  }

  @Test
  public void testFailedEvaluation() {
    List<Answer> answers = List.of(new Answer(new Question("question", "answer"), "wrong"));

    List<VerifiedAnswer> verifiedAnswers = evaluationService.evaluate(answers);

    assertEquals(1, verifiedAnswers.size());
    assertFalse(verifiedAnswers.iterator().next().isCorrect());

  }

}