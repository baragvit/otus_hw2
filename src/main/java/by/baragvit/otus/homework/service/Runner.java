package by.baragvit.otus.homework.service;

import by.baragvit.otus.homework.model.Answer;

import java.util.List;

public abstract class Runner {
  public void run() {
    String userName = getUserName();
    List<Answer> answers = getUserAnswers(userName);
    boolean hasPass = getGrade(answers);
    printResult(hasPass);
  }

  protected abstract void printResult(boolean hasPass);

  protected abstract boolean getGrade(List<Answer> answers);

  protected abstract List<Answer> getUserAnswers(String userName);

  protected abstract String getUserName();
}
