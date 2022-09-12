package by.baragvit.otus.homework.service;

import by.baragvit.otus.homework.model.VerifiedAnswer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimpleGradeService implements GradeService {
  @Override
  public boolean hasPass(List<VerifiedAnswer> verifiedAnswerList, double pct) {
    long correctAnswers = verifiedAnswerList.stream()
       .filter(VerifiedAnswer::isCorrect)
       .count();
    return ((double) correctAnswers) / verifiedAnswerList.size() >= pct;
  }
}
