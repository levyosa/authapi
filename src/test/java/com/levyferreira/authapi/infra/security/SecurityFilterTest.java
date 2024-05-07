package com.levyferreira.authapi.infra.security;

import com.levyferreira.authapi.repositories.UserRepository;
import com.levyferreira.authapi.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SecurityFilterTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @Mock
    private UserService userService;

    @Mock
    private UserDetails userDetails;

    private SecurityFilter securityFilter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        securityFilter = new SecurityFilter();
        securityFilter.tokenService = mock(TokenService.class);
        securityFilter.userService = userService;
        SecurityContextHolder.clearContext(); // Clear the SecurityContextHolder

    }

    @Test
    void shouldAuthenticateUserWhenTokenIsValid() throws Exception {
        when(request.getHeader("Authorization")).thenReturn("Bearer validToken");
        when(securityFilter.tokenService.validateToken("validToken")).thenReturn("validEmail");
        when(userService.loadUserByUsername("validEmail")).thenReturn(userDetails);

        securityFilter.doFilterInternal(request, response, filterChain);

        verify(filterChain).doFilter(request, response);
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        assertTrue(authentication instanceof UsernamePasswordAuthenticationToken);
        assertEquals(userDetails, authentication.getPrincipal());
    }

    @Test
    void shouldNotAuthenticateUserWhenTokenIsInvalid() throws Exception {
        when(request.getHeader("Authorization")).thenReturn("Bearer invalidToken");
        when(securityFilter.tokenService.validateToken("invalidToken")).thenReturn(null);

        securityFilter.doFilterInternal(request, response, filterChain);

        verify(filterChain).doFilter(request, response);
        assertNull(SecurityContextHolder.getContext().getAuthentication());
    }

    @Test
    void shouldNotAuthenticateUserWhenNoToken() throws Exception {
        when(request.getHeader("Authorization")).thenReturn(null);

        securityFilter.doFilterInternal(request, response, filterChain);

        verify(filterChain).doFilter(request, response);
        assertNull(SecurityContextHolder.getContext().getAuthentication());
    }
}