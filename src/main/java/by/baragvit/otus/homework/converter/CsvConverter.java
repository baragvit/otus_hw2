package by.baragvit.otus.homework.converter;

import by.baragvit.otus.homework.model.Question;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class CsvConverter {
  public List<Question> convert(List<String[]> allRows) {
    return allRows.stream()
       .map(line -> new Question(line[0], line[1]))
       .collect(Collectors.toList());
  }
}
