package dev.borriguel.jobflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableReactiveMongoAuditing;

@SpringBootApplication
@EnableReactiveMongoAuditing
public class JobfluxApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobfluxApplication.class, args);
    }

}
