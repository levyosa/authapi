package com.levyferreira.authapi.infra.security;

import com.levyferreira.authapi.domain.user.User;
import com.levyferreira.authapi.helper.FixtureHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.platform.commons.util.ReflectionUtils;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class TokenServiceTest {

    private final String SECRET = "Shhh";

    @Mock
    private User user;

    private TokenService tokenService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        tokenService = new TokenService();
        FixtureHelper.setField(tokenService, "secret", SECRET);
    }

    @Test
    void shouldGenerateTokenForUser() {
        when(user.getEmail()).thenReturn("test@test.com");
        String token = tokenService.generateToken(user);
        assertNotNull(token);
    }

    @Test
    void shouldValidateTokenAndReturnEmail() {
        when(user.getEmail()).thenReturn("test@test.com");
        String token = tokenService.generateToken(user);
        String email = tokenService.validateToken(token);
        assertEquals("test@test.com", email);
    }

    @Test
    void shouldReturnEmptyStringForInvalidToken() {
        String email = tokenService.validateToken("invalidToken");
        assertEquals("", email);
    }
}