package com.levyferreira.authapi.domain.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserRoleTest {

    @Test
    void shouldReturnCorrectAuthForUser() {
        assertEquals("ROLE_USER", UserRole.USER.getAuthority());
    }

    @Test
    void shouldReturnCorrectAuthForAdmin() {
        assertEquals("ROLE_ADMIN", UserRole.ADMIN.getAuthority());
    }

    @Test
    void shouldReturnCorrectRoleForUser() {
        assertEquals("user", UserRole.USER.getRole());
    }

    @Test
    void shouldReturnCorrectRoleForAdmin() {
        assertEquals("admin", UserRole.ADMIN.getRole());
    }
}