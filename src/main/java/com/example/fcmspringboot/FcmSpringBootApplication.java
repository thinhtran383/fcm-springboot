package com.example.fcmspringboot;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import java.io.IOException;

@SpringBootApplication
@EnableWebSecurity
@EnableJpaRepositories
public class FcmSpringBootApplication {

    @Bean
    FirebaseMessaging firebaseMessaging() throws IOException {
        GoogleCredentials googleCredentials = GoogleCredentials.fromStream(
                new ClassPathResource("food-ordering-1666f-firebase-adminsdk-pel1z-cba53403f1.json").getInputStream()
        );

        FirebaseOptions firebaseOptions = FirebaseOptions.builder()
                .setCredentials(googleCredentials).build();

        FirebaseApp app = FirebaseApp.initializeApp(firebaseOptions, "my-app");
        return FirebaseMessaging.getInstance(app);
    }




    public static void main(String[] args) {
        SpringApplication.run(FcmSpringBootApplication.class, args);
    }

}
