package by.baragvit.otus.homework;

import by.baragvit.otus.homework.propertieds.ApplicationProps;
import by.baragvit.otus.homework.service.Runner;
import by.baragvit.otus.homework.service.SimpleRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
@EnableConfigurationProperties(ApplicationProps.class)
public class App {
  public static void main(String[] args) {
    ApplicationContext context = SpringApplication.run(App.class);
    Runner runner = context.getBean(SimpleRunner.class);
    runner.run();
  }
}
