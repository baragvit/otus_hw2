package by.baragvit.otus.homework.service;

import java.util.Scanner;
import org.springframework.stereotype.Service;

@Service
public class CliReader implements Reader {
  private final Scanner scanner;

  public CliReader() {
    this.scanner = new Scanner(System.in);
  }

  @Override
  public String read() {
    return scanner.nextLine();
  }
}
