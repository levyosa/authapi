package com.levyferreira.authapi.domain.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class RegisterDTOTest {

    @Test
    void shouldReturnCorrectEmail() {
        RegisterDTO registerDTO = new RegisterDTO("test@test.com", "password", UserRole.USER);
        assertEquals("test@test.com", registerDTO.email());
    }

    @Test
    void shouldReturnCorrectPassword() {
        RegisterDTO registerDTO = new RegisterDTO("test@test.com", "password", UserRole.USER);
        assertEquals("password", registerDTO.password());
    }

    @Test
    void shouldReturnCorrectRole() {
        RegisterDTO registerDTO = new RegisterDTO("test@test.com", "password", UserRole.USER);
        assertEquals(UserRole.USER, registerDTO.role());
    }

    @Test
    void shouldHandleEmptyEmail() {
        RegisterDTO registerDTO = new RegisterDTO("", "password", UserRole.USER);
        assertEquals("", registerDTO.email());
    }

    @Test
    void shouldHandleEmptyPassword() {
        RegisterDTO registerDTO = new RegisterDTO("test@test.com", "", UserRole.USER);
        assertEquals("", registerDTO.password());
    }

    @Test
    void shouldHandleNullEmail() {
        RegisterDTO registerDTO = new RegisterDTO(null, "password", UserRole.USER);
        assertNull(registerDTO.email());
    }

    @Test
    void shouldHandleNullPassword() {
        RegisterDTO registerDTO = new RegisterDTO("test@test.com", null, UserRole.USER);
        assertNull(registerDTO.password());
    }

    @Test
    void shouldHandleNullRole() {
        RegisterDTO registerDTO = new RegisterDTO("test@test.com", "password", null);
        assertNull(registerDTO.role());
    }
}