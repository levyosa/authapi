package com.levyferreira.authapi.domain.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserDTOTest {

    @Test
    void shouldReturnCorrectEmail() {
        UserDTO authDTO = new UserDTO("test@test.com", "password");
        assertEquals("test@test.com", authDTO.email());
    }

    @Test
    void shouldReturnCorrectPassword() {
        UserDTO authDTO = new UserDTO("test@test.com", "password");
        assertEquals("password", authDTO.password());
    }

    @Test
    void shouldHandleEmptyEmail() {
        UserDTO authDTO = new UserDTO("", "password");
        assertEquals("", authDTO.email());
    }

    @Test
    void shouldHandleEmptyPassword() {
        UserDTO authDTO = new UserDTO("test@test.com", "");
        assertEquals("", authDTO.password());
    }

    @Test
    void shouldHandleNullEmail() {
        UserDTO authDTO = new UserDTO(null, "password");
        assertEquals(null, authDTO.email());
    }

    @Test
    void shouldHandleNullPassword() {
        UserDTO authDTO = new UserDTO("test@test.com", null);
        assertEquals(null, authDTO.password());
    }
}