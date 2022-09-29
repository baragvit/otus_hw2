package by.baragvit.otus.homework.service;

public interface MessageProvider {
  String getUserNameRepeatedly();
  String getCongrat();
  String getFailure();
  String getQuestionsRequest(String name);
  String getUserName();
}