package by.baragvit.otus.homework.service;

import by.baragvit.otus.homework.model.Answer;
import by.baragvit.otus.homework.model.Question;
import by.baragvit.otus.homework.model.VerifiedAnswer;
import by.baragvit.otus.homework.propertieds.ApplicationProps;
import java.util.List;
import java.util.Locale;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;

@ExtendWith(MockitoExtension.class)
class RunnerTest {
  @Mock
  private CliWriter writer;
  @Mock
  private CliReader reader;
  @Mock
  private QuestionServiceImpl questionService;
  @Mock
  private SimpleEvaluationService evaluationService;
  @Mock
  private SimpleGradeService gradingService;
  @Mock
  private MessageSource messageSource;

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
    ApplicationProps applicationProps = new ApplicationProps("path", 1d, Locale.getDefault());
    when(messageSource.getMessage(eq("hello.user"), any(), any()))
        .thenReturn("Hi, pls enter your surname and name: ");
    when(messageSource.getMessage(eq("answer.request"), any(), any()))
        .thenReturn("question answer\n");
    when(messageSource.getMessage(eq("result.success"), any(), any()))
        .thenReturn("Congratulations, you have passed the test\n");

    new SimpleRunner(writer, reader, questionService, evaluationService, gradingService, applicationProps, messageSource).run();

    verify(writer).write("Hi, pls enter your surname and name: ");
    verify(questionService).getQuestions();
    verify(writer).write("---> QuestionText: ");
    verify(reader, Mockito.times(2)).read();
    verify(evaluationService).evaluate(answers);
    verify(gradingService).hasPass(List.of(verifiedAnswer), 1);
    verify(writer).write("Congratulations, you have passed the test\n\n");
  }
}