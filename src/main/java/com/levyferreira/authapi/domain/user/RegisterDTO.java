package com.levyferreira.authapi.domain.user;

public record RegisterDTO(String email, String password, UserRole role) {
}
