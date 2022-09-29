package by.baragvit.otus.homework.service;

import by.baragvit.otus.homework.propertieds.ApplicationProps;
import java.util.Locale;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LocaleDependentFilePathProviderTest {


  @Test
  @DisplayName("Provide correct file path for given locale")
  void testProvideCorrectFilePathForStagedLocale() {
    ApplicationProps appProps = new ApplicationProps("/questions", 1d, Locale.forLanguageTag("en"));
    FilePathProvider pathProvider = new LocaleDependentFilePathProvider(appProps);

    assertEquals("/questions/questions_en.csv", pathProvider.getFilePath());

  }
}