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
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RunnerTest {
  @Mock
  private IOService ioService;
  @Mock
  private QuestionServiceImpl questionService;
  @Mock
  private SimpleEvaluationService evaluationService;
  @Mock
  private SimpleGradeService gradingService;
  @Mock
  private MessageProvider messageProvider;

  @Test
  public void testSuccessTest() {
    Question question = new Question("QuestionText", "Question Answer");
    List<Question> questions = List.of(question);
    Answer answer = new Answer(question, "Question Answer");
    List<Answer> answers = List.of(answer);
    VerifiedAnswer verifiedAnswer = new VerifiedAnswer(question, "Question Answer", true);
    when(questionService.getQuestions()).thenReturn(questions);
    when(ioService.read()).thenReturn("Question Answer");
    when(evaluationService.evaluate(answers)).thenReturn(List.of(verifiedAnswer));
    when(gradingService.hasPass(List.of(verifiedAnswer), 1d)).thenReturn(true);
    ApplicationProps applicationProps = new ApplicationProps("path", 1d, Locale.getDefault());
    when(messageProvider.getUserName())
        .thenReturn("Hi, pls enter your surname and name: ");
    when(messageProvider.getQuestionsRequest(any()))
        .thenReturn("question answer\n");
    when(messageProvider.getCongrat())
        .thenReturn("Congratulations, you have passed the test\n");

    new SimpleRunner(ioService, questionService, evaluationService, gradingService, applicationProps, messageProvider).run();

    verify(ioService).write("Hi, pls enter your surname and name: ");
    verify(questionService).getQuestions();
    verify(ioService).write("---> QuestionText: ");
    verify(ioService, Mockito.times(2)).read();
    verify(evaluationService).evaluate(answers);
    verify(gradingService).hasPass(List.of(verifiedAnswer), 1);
    verify(ioService).write("Congratulations, you have passed the test\n\n");
  }
}