package by.baragvit.otus.homework.service;

import by.baragvit.otus.homework.model.Question;
import by.baragvit.otus.homework.model.VerifiedAnswer;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SimpleGradeServiceTest {

  private SimpleGradeService simpleGradeService;

  @BeforeEach
  public void setUP() {
    simpleGradeService = new SimpleGradeService();
  }

  @Test
  public void testSuccessPassingGrade() {
    List<VerifiedAnswer> answers = List.of(new VerifiedAnswer(new Question(null, null), null, true));
    boolean hasPass = simpleGradeService.hasPass(answers, 1);

    assertTrue(hasPass);
  }

  @Test
  public void testFailedPassingGrade() {
    List<VerifiedAnswer> answers = List.of(new VerifiedAnswer(new Question(null, null), null, false));
    boolean hasPass = simpleGradeService.hasPass(answers, 1);

    assertFalse(hasPass);
  }

}