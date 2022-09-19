package by.baragvit.otus.homework.dao;

import by.baragvit.otus.homework.converter.CsvConverter;
import by.baragvit.otus.homework.model.Question;
import by.baragvit.otus.homework.utils.DataParser;
import by.baragvit.otus.homework.utils.ReaderProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

@Repository
public class QuestionDaoCsv implements QuestionDao {
  private final ReaderProvider readerProvider;
  private final DataParser dataParser;
  private final String filePath;
  private final CsvConverter csvConverter;

  public QuestionDaoCsv(@Value("${filePath}") String filePath,
                        ReaderProvider readerProvider,
                        DataParser dataParser,
                        CsvConverter csvConverter) {
    this.readerProvider = readerProvider;
    this.filePath = filePath;
    this.dataParser = dataParser;
    this.csvConverter = csvConverter;
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
