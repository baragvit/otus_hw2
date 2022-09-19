package by.baragvit.otus.homework.service;

import by.baragvit.otus.homework.model.Answer;
import org.springframework.stereotype.Service;
import by.baragvit.otus.homework.model.VerifiedAnswer;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SimpleEvaluationService implements EvaluationService {
  @Override
  public List<VerifiedAnswer> evaluate(List<Answer> answers) {
    return answers.stream()
       .map(ans -> new VerifiedAnswer(ans.getQuestion(), ans.getAnswer(), ans.getQuestion().getAnswerText().equals(ans.getAnswer())))
       .collect(Collectors.toList());
  }
}
