package by.baragvit.otus.homework.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import by.baragvit.otus.homework.converter.CsvConverter;
import by.baragvit.otus.homework.utils.DataParser;
import by.baragvit.otus.homework.model.Question;
import by.baragvit.otus.homework.utils.ReaderProvider;

import java.io.Reader;
import java.util.List;

@Repository
public class QuestionDaoCsv implements QuestionDao {
  private final ReaderProvider readerProvider;
  private final DataParser dataParser;
  private final String filePath;

  public QuestionDaoCsv(@Value("${filePath}") String filePath, ReaderProvider readerProvider, DataParser dataParser) {
    this.readerProvider = readerProvider;
    this.filePath = filePath;
    this.dataParser = dataParser;
  }

  @Override
  public List<Question> getQuestions() {
    Reader dataReader = readerProvider.getDataReader(filePath);
    List<String[]> allRows = dataParser.getLines(dataReader);
    return CsvConverter.convert(allRows);
  }
}
