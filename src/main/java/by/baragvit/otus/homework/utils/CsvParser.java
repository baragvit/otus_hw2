package by.baragvit.otus.homework.utils;

import by.baragvit.otus.homework.exception.CsvFileException;
import com.opencsv.CSVReader;
import java.io.Reader;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CsvParser implements DataParser {
  @Override
  public List<String[]> getLines(Reader reader) {
    try {
      return new CSVReader(reader).readAll();
    } catch (Exception e) {
      throw new CsvFileException(e);
    }
  }
}
