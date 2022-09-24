package by.baragvit.otus.homework;

import by.baragvit.otus.homework.service.SimpleRunner;
import by.baragvit.otus.homework.service.Runner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;


@SpringBootApplication
public class App {
  public static void main(String[] args) {
    ApplicationContext context = SpringApplication.run(App.class);
    Runner runner = context.getBean(SimpleRunner.class);
    runner.run();
  }
}
