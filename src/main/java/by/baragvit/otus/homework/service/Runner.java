package by.baragvit.otus.homework.service;

import by.baragvit.otus.homework.model.Answer;

import by.baragvit.otus.homework.model.User;
import java.util.List;

public abstract class Runner {
  public void run() {
    User user = getUserName();
    List<Answer> answers = getUserAnswers(user);
    boolean hasPass = getGrade(answers);
    printResult(hasPass);
  }

  protected abstract void printResult(boolean hasPass);

  protected abstract boolean getGrade(List<Answer> answers);

  protected abstract List<Answer> getUserAnswers(User userName);

  protected abstract User getUserName();
}
