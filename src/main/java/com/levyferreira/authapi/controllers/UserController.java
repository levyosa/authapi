package com.levyferreira.authapi.controllers;

import com.levyferreira.authapi.domain.user.UserDTO;
import com.levyferreira.authapi.domain.user.LoginResponseDTO;
import com.levyferreira.authapi.domain.user.RegisterDTO;
import com.levyferreira.authapi.domain.user.User;
import com.levyferreira.authapi.infra.security.TokenService;
import com.levyferreira.authapi.repositories.UserRepository;
import com.levyferreira.authapi.services.EmailService;
import com.levyferreira.authapi.services.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid UserDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<String> register(@RequestBody @Valid RegisterDTO data) {
        if (this.userService.existsByEmail(data.email())) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Email already exists");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.email(), encryptedPassword, data.role());

        this.userService.save(newUser);
        emailService.sendEmail(newUser);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Page<User>> getUsers(Pageable pageable) {
        return ResponseEntity.ok(this.userService.findAll(pageable));
    }
}


