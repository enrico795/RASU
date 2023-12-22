package it.comune.verona.rasu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;

@SpringBootApplication
@EnableScheduling
public class RasuApplication {

    public static void main(String[] args) {
        SpringApplication.run(RasuApplication.class, args);
    }

}
