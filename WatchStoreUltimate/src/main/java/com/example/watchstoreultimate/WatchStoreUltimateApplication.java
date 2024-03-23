package com.example.watchstoreultimate;

import com.example.watchstoreultimate.service.MailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WatchStoreUltimateApplication implements CommandLineRunner {

    public static void main(String[] args) throws MessagingException {
        SpringApplication.run(WatchStoreUltimateApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        System.out.println(mailService.sendMail("presidentquyen@gmail.com", 213));
    }
}
