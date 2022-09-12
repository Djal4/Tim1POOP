package com.ldg.main;

import org.springframework.stereotype.Component;
import org.springframework.web.cors.*;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.*;

import java.util.*;

import org.springframework.beans.factory.annotation.Value;

@Component
public class AllBeans {
    @Value("${spring.mail.host}")
    private String mailHost;

    @Value("${spring.mail.port}")
    private int mailPort;

    @Value("${spring.mail.username}")
    private String senderMail;

    @Value("${spring.mail.password}")
    private String senderPassword;

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost(mailHost);
        mailSender.setPort(mailPort);

        mailSender.setUsername(senderMail);
        mailSender.setPassword(senderPassword);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        return mailSender;
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // Allowed React app, Swagger docs and Angular app on localhost
        configuration.setAllowedOrigins(
                Arrays.asList("http://localhost:3000", "http://localhost:8888", "http://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PATCH", "DELETE", "OPTIONS"));
        configuration.addAllowedHeader("authorization");
        configuration.addAllowedHeader("content-type");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
