package by.baragvit.otus.homework.service;

import java.io.PrintWriter;
import org.springframework.stereotype.Service;

@Service
public class CliWriter implements Writer {
  private final PrintWriter writer;

  public CliWriter() {
    writer = new PrintWriter(System.out);
  }

  @Override
  public void write(String data) {
    writer.write(data);
    writer.flush();
  }
}
