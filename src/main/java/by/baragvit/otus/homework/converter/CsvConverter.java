package by.baragvit.otus.homework.converter;

import by.baragvit.otus.homework.model.Question;

import java.util.List;
import java.util.stream.Collectors;

public class CsvConverter {
  public static List<Question> convert(List<String[]> allRows) {
    return allRows.stream()
       .map(line -> new Question(line[0], line[1]))
       .collect(Collectors.toList());
  }
}
