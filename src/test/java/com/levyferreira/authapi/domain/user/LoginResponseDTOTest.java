package com.levyferreira.authapi.domain.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LoginResponseDTOTest {

    @Test
    void shouldReturnCorrectToken() {
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO("testToken");
        assertEquals("testToken", loginResponseDTO.token());
    }

    @Test
    void shouldHandleEmptyToken() {
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO("");
        assertEquals("", loginResponseDTO.token());
    }

    @Test
    void shouldHandleNullToken() {
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO(null);
        assertEquals(null, loginResponseDTO.token());
    }
}