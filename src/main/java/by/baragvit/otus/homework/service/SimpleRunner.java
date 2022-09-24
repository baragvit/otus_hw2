package by.baragvit.otus.homework.service;

import by.baragvit.otus.homework.model.Answer;
import by.baragvit.otus.homework.model.Question;
import by.baragvit.otus.homework.model.User;
import by.baragvit.otus.homework.model.VerifiedAnswer;
import by.baragvit.otus.homework.propertieds.ApplicationProps;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
public class SimpleRunner extends Runner {
  public static final String RESULT_SUCCESS = "result.success";
  public static final String RESULT_FAILURE = "result.failure";
  public static final String ANSWER_REQUEST = "answer.request";
  public static final String HELLO_USER = "hello.user";
  public static final String INCORRECT_NAME = "incorrect.name";
  private final Writer writer;
  private final Reader reader;
  private final QuestionService questionService;
  private final EvaluationService evaluationService;
  private final GradeService gradeService;
  private final double passRate;
  private final MessageSource messageSource;
  private final Locale locale;

  public SimpleRunner(Writer writer,
                      Reader reader,
                      QuestionService questionService,
                      EvaluationService evaluationService,
                      GradeService gradeService,
                      ApplicationProps applicationProps,
                      MessageSource messageSource) {
    this.writer = writer;
    this.reader = reader;
    this.questionService = questionService;
    this.evaluationService = evaluationService;
    this.gradeService = gradeService;
    this.passRate = applicationProps.passRate();
    this.messageSource = messageSource;
    this.locale = applicationProps.locale();
  }

  @Override
  protected void printResult(boolean hasPass) {
    String result;
    if (hasPass) {
      result = messageSource.getMessage(RESULT_SUCCESS, new String[]{}, locale);
    } else {
      result = messageSource.getMessage(RESULT_FAILURE, new String[]{}, locale);
    }
    writer.write(String.format("%s\n", result));
  }

  @Override
  protected boolean getGrade(List<Answer> answers) {
    List<VerifiedAnswer> verifiedAnswers = evaluationService.evaluate(answers);
    return gradeService.hasPass(verifiedAnswers, passRate);
  }

  @Override
  protected List<Answer> getUserAnswers(User user) {
    String answersRequest = messageSource.getMessage(ANSWER_REQUEST, new String[]{user.getFirstName()}, locale);
    writer.write(String.format("%s\n", answersRequest));
    List<Question> questions = questionService.getQuestions();
    List<Answer> answers = new ArrayList<>();
    for (Question question : questions) {
      writer.write(String.format("---> %s: ", question.getQuestionText()));
      String answer = reader.read();
      answers.add(new Answer(question, answer));
    }
    return answers;
  }

  @Override
  protected User getUserName() {
    String helloUserMessage = messageSource.getMessage(HELLO_USER, new String[]{}, locale);
    writer.write(helloUserMessage);
    do {
      String rawName = reader.read().strip();
      String[] splittedName = rawName.split(" ");
      if (splittedName.length < 2) {
        String incorrectNameMessage = messageSource.getMessage(INCORRECT_NAME, new String[]{}, locale);
        writer.write(String.format("%s ", incorrectNameMessage));
      } else {
        return new User().setFirstName(splittedName[0]).setLastName(splittedName[1]);
      }
    } while (true);
  }
}
