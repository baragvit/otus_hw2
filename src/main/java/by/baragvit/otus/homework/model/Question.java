package by.baragvit.otus.homework.model;

import java.util.Objects;

public class Question {
  private String questionText;
  private String answerText;

  public Question(String text, String answer) {
    this.questionText = text;
    this.answerText = answer;
  }

  public String getQuestionText() {
    return questionText;
  }

  public String getAnswerText() {
    return answerText;
  }

  @Override
  public String toString() {
    return "Question: " + questionText + "\n\tAnswer:" + answerText;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Question question = (Question) o;
    return Objects.equals(questionText, question.questionText) && Objects.equals(answerText, question.answerText);
  }

  @Override
  public int hashCode() {
    return Objects.hash(questionText, answerText);
  }
}
