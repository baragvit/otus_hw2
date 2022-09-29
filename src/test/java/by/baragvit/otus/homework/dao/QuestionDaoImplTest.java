package by.baragvit.otus.homework.dao;

import by.baragvit.otus.homework.converter.CsvConverter;
import by.baragvit.otus.homework.model.Question;
import by.baragvit.otus.homework.service.FilePathProvider;
import by.baragvit.otus.homework.utils.CsvParser;
import by.baragvit.otus.homework.utils.ReaderProvider;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class QuestionDaoImplTest {

  @Mock
  private ReaderProvider readerProvider;
  @Mock
  private CsvParser csvParser;
  @Spy
  private CsvConverter csvConverter = new CsvConverter();
  @Mock
  private FilePathProvider filePathProvider;


  @Test
  public void getQuestionsFromFile() {
    when(readerProvider.getDataReader(any())).thenReturn(new StringReader("some data"));
    String[] row = {"one", "two"};
    List<String[]> rows = new ArrayList<>();
    rows.add(row);
    when(csvParser.getLines(any())).thenReturn(rows);
    QuestionDao questionDaoCsv = new QuestionDaoCsv(filePathProvider, readerProvider, csvParser, csvConverter);

    List<Question> questions = questionDaoCsv.getQuestions();

    assertNotNull(questions);
    assertEquals(List.of(new Question("one", "two")), questions);
  }
}