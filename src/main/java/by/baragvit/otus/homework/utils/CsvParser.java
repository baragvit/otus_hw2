package by.baragvit.otus.homework.utils;

import com.opencsv.CSVReader;
import org.springframework.stereotype.Service;
import by.baragvit.otus.homework.exception.CsvFileException;

import java.io.Reader;
import java.util.List;

@Service
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
