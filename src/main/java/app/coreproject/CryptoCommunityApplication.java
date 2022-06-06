package app.coreproject;

import app.coreproject.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;


@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
@EnableAsync
public class CryptoCommunityApplication {

    public static void main(String[] args) {
        SpringApplication.run(CryptoCommunityApplication.class, args);
    }

}
