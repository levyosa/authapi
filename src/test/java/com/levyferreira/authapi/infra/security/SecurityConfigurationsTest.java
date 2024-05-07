package com.levyferreira.authapi.infra.security;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
class SecurityConfigurationsTest {

    @Autowired
    private SecurityConfigurations securityConfigurations;

    @Mock
    private HttpSecurity httpSecurity;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        when(httpSecurity.authorizeRequests()).thenReturn(null);
    }

    @Test
    void shouldCreateSecurityFilterChain() throws Exception {
        SecurityFilterChain securityFilterChain = securityConfigurations.securityFilterChain(httpSecurity);
        assertNotNull(securityFilterChain);
    }

    @Test
    void shouldCreateAuthenticationManager() throws Exception {
        assertNotNull(securityConfigurations.authenticationManager(null));
    }

    @Test
    void shouldCreatePasswordEncoder() {
        assertNotNull(securityConfigurations.passwordEncoder());
    }
}