package com.levyferreira.authapi.services;
import com.levyferreira.authapi.helper.FixtureHelper;
import com.levyferreira.authapi.services.UserService;
import com.levyferreira.authapi.repositories.UserRepository;
import com.levyferreira.authapi.domain.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService();
        FixtureHelper.setField(userService, "repository", userRepository);
    }

    @Test
    public void loadUserByUsernameReturnsUserDetailsWhenEmailExists() {
        String email = "test@test.com";
        User user = new User();
        user.setEmail(email);
        when(userRepository.findByEmail(email)).thenReturn(user);

        UserDetails result = userService.loadUserByUsername(email);

        assertEquals(user, result);
    }

    @Test
    public void loadUserByUsernameThrowsExceptionWhenEmailDoesNotExist() {
        String email = "nonexistent@test.com";
        when(userRepository.findByEmail(email)).thenReturn(null);

        Exception exception = assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername(email));
        assertEquals("User not found with email: " + email, exception.getMessage());
    }
}