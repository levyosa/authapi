package com.levyferreira.authapi.services;

import com.levyferreira.authapi.domain.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class EmailServiceTest {

    @Mock
    private EmailService emailService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void sendEmailToUserDoesNotThrowException() {
        User user = new User();
        user.setEmail("test@test.com");
        emailService.sendEmail(user);
    }
}