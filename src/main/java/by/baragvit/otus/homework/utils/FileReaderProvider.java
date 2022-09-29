package by.baragvit.otus.homework.utils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import org.springframework.stereotype.Component;

@Component
public class FileReaderProvider implements ReaderProvider {

  @Override
  public Reader getDataReader(String path) {
    InputStream csvStream = Thread.currentThread()
        .getContextClassLoader()
        .getResourceAsStream(path);
    return new InputStreamReader(csvStream);
  }
}
