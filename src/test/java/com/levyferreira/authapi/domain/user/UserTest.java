package com.levyferreira.authapi.domain.user;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void shouldReturnCorrectEmail() {
        User user = new User("test@test.com", "password", UserRole.USER);
        assertEquals("test@test.com", user.getUsername());
    }

    @Test
    void shouldReturnCorrectPassword() {
        User user = new User("test@test.com", "password", UserRole.USER);
        assertEquals("password", user.getPassword());
    }

    @Test
    void shouldReturnCorrectRole() {
        User user = new User("test@test.com", "password", UserRole.USER);
        assertTrue(user.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER")));
    }

    @Test
    void shouldReturnAdminRole() {
        User user = new User("test@test.com", "password", UserRole.ADMIN);
        assertTrue(user.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")));
    }

    @Test
    void shouldReturnUserAndAdminRoleForAdmin() {
        User user = new User("test@test.com", "password", UserRole.ADMIN);
        assertTrue(user.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER")));
        assertTrue(user.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")));
    }

    @Test
    void shouldReturnTrueForAccountNonExpired() {
        User user = new User("test@test.com", "password", UserRole.USER);
        assertTrue(user.isAccountNonExpired());
    }

    @Test
    void shouldReturnTrueForAccountNonLocked() {
        User user = new User("test@test.com", "password", UserRole.USER);
        assertTrue(user.isAccountNonLocked());
    }

    @Test
    void shouldReturnTrueForCredentialsNonExpired() {
        User user = new User("test@test.com", "password", UserRole.USER);
        assertTrue(user.isCredentialsNonExpired());
    }

    @Test
    void shouldReturnTrueForEnabled() {
        User user = new User("test@test.com", "password", UserRole.USER);
        assertTrue(user.isEnabled());
    }

    @Test
    void shouldHandleEmptyEmail() {
        User user = new User("", "password", UserRole.USER);
        assertEquals("", user.getUsername());
    }

    @Test
    void shouldHandleNullEmail() {
        User user = new User(null, "password", UserRole.USER);
        assertNull(user.getUsername());
    }

    @Test
    void shouldHandleNullRole() {
        User user = new User("test@test.com", "password", null);
        assertTrue(user.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER")));
    }
}