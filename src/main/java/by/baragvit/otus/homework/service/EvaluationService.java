package by.baragvit.otus.homework.service;

import by.baragvit.otus.homework.model.Answer;
import by.baragvit.otus.homework.model.VerifiedAnswer;
import java.util.List;

public interface EvaluationService {
  List<VerifiedAnswer> evaluate(List<Answer> answers);
}
