package it.java.challenge;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ItJavaChallengeApplication {
	
	@PostConstruct
    public void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC-3"));
    }
	
	public static void main(String[] args) {
		SpringApplication.run(ItJavaChallengeApplication.class, args);
	}

}