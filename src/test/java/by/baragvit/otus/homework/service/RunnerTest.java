package by.baragvit.otus.homework.service;

import by.baragvit.otus.homework.model.Answer;
import by.baragvit.otus.homework.model.Question;
import by.baragvit.otus.homework.model.VerifiedAnswer;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RunnerTest {
  @Mock
  CliWriter writer;
  @Mock
  private CliReader reader;
  @Mock
  private QuestionServiceImpl questionService;
  @Mock
  private SimpleEvaluationService evaluationService;
  @Mock
  private SimpleGradeService gradingService;

  @Test
  public void testSuccessTest() {
    Question question = new Question("QuestionText", "Question Answer");
    List<Question> questions = List.of(question);
    Answer answer = new Answer(question, "Question Answer");
    List<Answer> answers = List.of(answer);
    VerifiedAnswer verifiedAnswer = new VerifiedAnswer(question, "Question Answer", true);
    when(questionService.getQuestions()).thenReturn(questions);
    when(reader.read()).thenReturn("Question Answer");
    when(evaluationService.evaluate(answers)).thenReturn(List.of(verifiedAnswer));
    when(gradingService.hasPass(List.of(verifiedAnswer), 1d)).thenReturn(true);

    new CliRunner(writer, reader, questionService, evaluationService, gradingService, 1d).run();

    verify(writer).write("Hi, pls enter your surname and name: ");
    verify(questionService).getQuestions();
    verify(writer).write("---> QuestionText: ");
    verify(reader, Mockito.times(2)).read();
    verify(evaluationService).evaluate(answers);
    verify(gradingService).hasPass(List.of(verifiedAnswer), 1);
    verify(writer).write("Congratulations, you have passed the test\n");
  }
}