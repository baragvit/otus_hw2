package by.baragvit.otus.homework.utils;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

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
