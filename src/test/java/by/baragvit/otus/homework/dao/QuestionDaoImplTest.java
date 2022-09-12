package by.baragvit.otus.homework.dao;

import by.baragvit.otus.homework.model.Question;
import by.baragvit.otus.homework.utils.CsvParser;
import by.baragvit.otus.homework.utils.FileReaderProvider;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class QuestionDaoImplTest {

  @Mock
  private FileReaderProvider fileDataReader;
  @Mock
  private CsvParser csvParser;
  @InjectMocks
  private QuestionDaoCsv questionDaoCsv;

  @Test
  public void getQuestionsFromFile() {
    String[] row = {"one", "two"};
    List<String[]> rows = new ArrayList<>();
    rows.add(row);
    when(csvParser.getLines(any())).thenReturn(rows);

    List<Question> questions = questionDaoCsv.getQuestions();

    assertNotNull(questions);
    assertEquals(List.of(new Question("one", "two")), questions);
  }
}