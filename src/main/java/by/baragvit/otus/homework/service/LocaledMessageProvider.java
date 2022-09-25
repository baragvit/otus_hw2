package by.baragvit.otus.homework.service;

import by.baragvit.otus.homework.propertieds.ApplicationProps;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
public class LocaledMessageProvider implements MessageProvider {
  public static final String RESULT_SUCCESS = "result.success";
  public static final String RESULT_FAILURE = "result.failure";
  public static final String ANSWER_REQUEST = "answer.request";
  public static final String HELLO_USER = "hello.user";
  public static final String INCORRECT_NAME = "incorrect.name";
  private final LocaledMessageSource localedMessageSource;

  public LocaledMessageProvider(MessageSource messageSource,
                                ApplicationProps applicationProps,
                                LocaledMessageSource localedMessageSource) {
    this.localedMessageSource = localedMessageSource;
  }

  public String getQuestionsRequest(String name) {
    return localedMessageSource.getMessage(ANSWER_REQUEST, name);
  }

  public String getUserName() {
    return localedMessageSource.getMessage(HELLO_USER);
  }

  public String getUserNameRepeatedly() {
    return localedMessageSource.getMessage(INCORRECT_NAME);
  }

  public String getCongrat() {
    return localedMessageSource.getMessage(RESULT_SUCCESS);
  }

  public String getFailure() {
    return localedMessageSource.getMessage(RESULT_FAILURE);
  }
}
