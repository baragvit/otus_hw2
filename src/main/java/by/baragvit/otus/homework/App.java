package by.baragvit.otus.homework;

import by.baragvit.otus.homework.service.SimpleRunner;
import by.baragvit.otus.homework.service.Runner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@ComponentScan
@PropertySource("classpath:application.properties")
public class App {
  public static void main(String[] args) {
    ApplicationContext context = new AnnotationConfigApplicationContext(App.class);
    Runner runner = context.getBean(SimpleRunner.class);
    runner.run();
  }
}
