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
      result = messageSource.getMessage("result.success", new String[]{}, locale);
    } else {
      result = messageSource.getMessage("result.failure", new String[]{}, locale);
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
    writer.write(String.format("%s, please answer the following questions:\n", user.getFirstName()));
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
    String message = messageSource.getMessage("hello.user", new String[]{}, locale);
    writer.write(message);
    do {
      String rawName = reader.read().strip();
      String[] splittedName = rawName.split(" ");
      if (splittedName.length < 2) {
        writer.write("Incorrect name, please, try again: ");
      } else {
        return new User().setFirstName(splittedName[0]).setLastName(splittedName[1]);
      }
    } while (true);
  }
}
