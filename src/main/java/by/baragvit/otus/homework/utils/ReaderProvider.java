package by.baragvit.otus.homework.utils;

import java.io.Reader;

public interface ReaderProvider {
  Reader getDataReader(String path);
}
