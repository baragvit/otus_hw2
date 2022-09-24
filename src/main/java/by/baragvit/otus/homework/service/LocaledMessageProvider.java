package by.baragvit.otus.homework.service;

import by.baragvit.otus.homework.propertieds.ApplicationProps;
import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
public class LocaledMessageProvider implements MessageProvider{
  public static final String RESULT_SUCCESS = "result.success";
  public static final String RESULT_FAILURE = "result.failure";
  public static final String ANSWER_REQUEST = "answer.request";
  public static final String HELLO_USER = "hello.user";
  public static final String INCORRECT_NAME = "incorrect.name";
  private final MessageSource messageSource;
  private final Locale locale;

  public LocaledMessageProvider(MessageSource messageSource, ApplicationProps applicationProps) {
    this.messageSource = messageSource;
    this.locale = applicationProps.locale();
  }

  private String getMessage(String templateName, String... args) {
    return messageSource.getMessage(templateName, args, locale);
  }

  public String getQuestionsRequest(String name) {
    return getMessage(ANSWER_REQUEST, name);
  }

  public String getUserName() {
    return getMessage(HELLO_USER);
  }

  public String getUserNameRepeatedly() {
    return getMessage(INCORRECT_NAME);
  }

  public String getCongrat() {
    return getMessage(RESULT_SUCCESS);
  }

  public String getFailure() {
    return getMessage(RESULT_FAILURE);
  }
}
