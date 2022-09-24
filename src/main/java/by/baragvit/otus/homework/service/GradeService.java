package by.baragvit.otus.homework.service;

import by.baragvit.otus.homework.model.VerifiedAnswer;
import java.util.List;


public interface GradeService {
  boolean hasPass(List<VerifiedAnswer> verifiedAnswerList, double pct);
}
