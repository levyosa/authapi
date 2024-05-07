package com.levyferreira.authapi.services;

import com.levyferreira.authapi.domain.user.User;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    public void sendEmail(User user) {
        System.out.println("Sending email to " + user.getEmail());
    }
}