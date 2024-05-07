package com.levyferreira.authapi.repositories;

import com.levyferreira.authapi.domain.user.User;
import com.levyferreira.authapi.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

public class UserRepositoryTest {

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findByEmailReturnsUserDetailsWhenEmailExists() {
        String email = "test@test.com";
        User user = new User();
        user.setEmail(email);
        when(userRepository.findByEmail(email)).thenReturn(user);

        UserDetails result = userRepository.findByEmail(email);

        assertEquals(user, result);
    }

    @Test
    public void findByEmailReturnsNullWhenEmailDoesNotExist() {
        String email = "nonexistent@test.com";
        when(userRepository.findByEmail(email)).thenReturn(null);

        UserDetails result = userRepository.findByEmail(email);

        assertNull(result);
    }
}