package by.baragvit.otus.homework.exception;

public class CsvFileException extends RuntimeException {
  public CsvFileException(Exception e) {
    super(e);
  }
}
