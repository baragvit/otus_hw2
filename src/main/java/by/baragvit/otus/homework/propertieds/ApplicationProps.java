package by.baragvit.otus.homework.propertieds;

import java.util.Locale;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties
public record ApplicationProps(String questionFilesPath, double passRate, Locale locale) {
}
