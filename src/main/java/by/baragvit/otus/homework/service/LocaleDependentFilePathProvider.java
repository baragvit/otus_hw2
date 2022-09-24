package by.baragvit.otus.homework.service;

import by.baragvit.otus.homework.propertieds.ApplicationProps;
import org.springframework.stereotype.Component;

@Component
public class LocaleDependentFilePathProvider implements FilePathProvider {
  public static final String QUESTION_PATH_TEMPLATE = "%s/questions_%s.csv";
  private final String filePath;

  public LocaleDependentFilePathProvider(ApplicationProps applicationProps) {
    this.filePath = String.format(QUESTION_PATH_TEMPLATE, applicationProps.questionFilesPath(), applicationProps.locale().getLanguage());;
  }

  @Override
  public String getFilePath() {
    return filePath;
  }
}
