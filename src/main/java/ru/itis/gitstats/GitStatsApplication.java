package ru.itis.gitstats;

import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
@EnableFeignClients
@EnableJpaRepositories
@EnableScheduling
public class GitStatsApplication {

    @Bean
    public ExecutorService executorService() {
        return Executors.newFixedThreadPool(2);
    }

    @SneakyThrows
    public static void main(String[] args) {
        SpringApplication.run(GitStatsApplication.class, args);
    }

}
