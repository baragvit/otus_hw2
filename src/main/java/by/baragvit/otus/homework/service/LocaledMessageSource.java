package by.baragvit.otus.homework.service;

import by.baragvit.otus.homework.propertieds.ApplicationProps;
import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class LocaledMessageSource {
  private final MessageSource messageSource;
  private final Locale locale;

  public LocaledMessageSource(MessageSource messageSource, ApplicationProps applicationProps) {
    this.messageSource = messageSource;
    this.locale = applicationProps.locale();
  }

  public String getMessage(String templateName, String... args) {
    return messageSource.getMessage(templateName, args, locale);
  }
}
