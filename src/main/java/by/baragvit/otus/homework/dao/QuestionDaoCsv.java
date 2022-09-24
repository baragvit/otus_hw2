package by.baragvit.otus.homework.dao;

import by.baragvit.otus.homework.converter.CsvConverter;
import by.baragvit.otus.homework.model.Question;
import by.baragvit.otus.homework.propertieds.ApplicationProps;
import by.baragvit.otus.homework.utils.DataParser;
import by.baragvit.otus.homework.utils.ReaderProvider;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class QuestionDaoCsv implements QuestionDao {
  public static final String QUESTION_PATH_TEMPLATE = "%s/questions_%s.csv";
  private final ReaderProvider readerProvider;
  private final DataParser dataParser;
  private final String filePath;
  private final CsvConverter csvConverter;

  public QuestionDaoCsv(ApplicationProps applicationProps,
                        ReaderProvider readerProvider,
                        DataParser dataParser,
                        CsvConverter csvConverter) {
    this.readerProvider = readerProvider;
    this.dataParser = dataParser;
    this.csvConverter = csvConverter;
    this.filePath = String.format(QUESTION_PATH_TEMPLATE, applicationProps.questionFilesPath(), applicationProps.locale().getLanguage());
  }

  @Override
  public List<Question> getQuestions() {
    List<String[]> allRows;
    try (Reader dataReader = readerProvider.getDataReader(filePath)) {
      allRows = dataParser.getLines(dataReader);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return csvConverter.convert(allRows);
  }
}
