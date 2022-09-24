package by.baragvit.otus.homework.propertieds;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties
public record ApplicationProps(String filePath, double passRate) {
}
