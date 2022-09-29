package by.baragvit.otus.homework.service;

import org.springframework.stereotype.Service;

@Service
public class CliIOService implements IOService {
  private final Reader reader;
  private final Writer writer;

  public CliIOService(Reader reader, Writer writer) {
    this.reader = reader;
    this.writer = writer;
  }

  @Override
  public String read() {
    return reader.read();
  }

  @Override
  public void write(String data) {
    writer.write(data);
  }
}
