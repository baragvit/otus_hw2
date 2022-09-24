package by.baragvit.otus.homework.service;

import by.baragvit.otus.homework.model.Answer;
import by.baragvit.otus.homework.model.Question;
import by.baragvit.otus.homework.model.User;
import by.baragvit.otus.homework.model.VerifiedAnswer;
import by.baragvit.otus.homework.propertieds.ApplicationProps;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class SimpleRunner extends Runner {
  private final IOService ioService;
  private final QuestionService questionService;
  private final EvaluationService evaluationService;
  private final GradeService gradeService;
  private final double passRate;
  private final MessageProvider messageProvider;

  public SimpleRunner(IOService ioService,
                      QuestionService questionService,
                      EvaluationService evaluationService,
                      GradeService gradeService,
                      ApplicationProps applicationProps,
                      MessageProvider messageProvider) {
    this.ioService = ioService;
    this.questionService = questionService;
    this.evaluationService = evaluationService;
    this.gradeService = gradeService;
    this.passRate = applicationProps.passRate();
    this.messageProvider = messageProvider;
  }

  @Override
  protected void printResult(boolean hasPass) {
    String result;
    if (hasPass) {
      result = messageProvider.getCongrat();
    } else {
      result = messageProvider.getFailure();
    }
    ioService.write(String.format("%s\n", result));
  }

  @Override
  protected boolean getGrade(List<Answer> answers) {
    List<VerifiedAnswer> verifiedAnswers = evaluationService.evaluate(answers);
    return gradeService.hasPass(verifiedAnswers, passRate);
  }

  @Override
  protected List<Answer> getUserAnswers(User user) {
    String answersRequest = messageProvider.getQuestionsRequest(user.getFirstName());
    ioService.write(String.format("%s\n", answersRequest));
    List<Question> questions = questionService.getQuestions();
    List<Answer> answers = new ArrayList<>();
    for (Question question : questions) {
      ioService.write(String.format("---> %s: ", question.getQuestionText()));
      String answer = ioService.read();
      answers.add(new Answer(question, answer));
    }
    return answers;
  }

  @Override
  protected User getUserName() {
    String helloUserMessage = messageProvider.getUserName();
    ioService.write(helloUserMessage);
    do {
      String rawName = ioService.read().strip();
      String[] splittedName = rawName.split(" ");
      if (splittedName.length < 2) {
        String incorrectNameMessage = messageProvider.getUserNameRepeatedly();
        ioService.write(String.format("%s ", incorrectNameMessage));
      } else {
        return new User().setFirstName(splittedName[0]).setLastName(splittedName[1]);
      }
    } while (true);
  }
}
