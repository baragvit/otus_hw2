package by.baragvit.otus.homework.utils;

import java.io.Reader;
import java.util.List;

public interface DataParser {
  List<String[]> getLines(Reader reader);
}

