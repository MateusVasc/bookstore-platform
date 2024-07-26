package com.matt.libraryapi.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import com.matt.libraryapi.domain.entities.User;
import com.matt.libraryapi.domain.requests.AuthRequest;
import com.matt.libraryapi.infra.JwtToken;
import com.matt.libraryapi.repository.UserRepository;
import com.matt.libraryapi.utils.BookstoreException;
import com.matt.libraryapi.utils.ErrorMessages;
import java.util.Optional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

  @Mock
  private UserRepository userRepository;

  @Mock
  private PasswordEncoder passwordEncoder;

  @Mock
  private AuthenticationManager authenticationManager;

  @Mock
  private JwtToken jwtToken;

  @InjectMocks
  private AuthService authService;

  @Nested
  class login {

    @Test
    void shouldLoginSuccessfully() {

    }

    @Test
    void shouldReturnUserNotFound() {

    }

    @Test
    void shouldReturnInvalidPassword() {

    }
  }

  @Nested
  class register {

    private static AuthRequest successRequest;
    private static AuthRequest invalidEmailRequest;
    private static AuthRequest invalidPasswordRequest;
    private static AuthRequest usedEmailRequest;

    @BeforeAll
    static void setUp() {
      successRequest = new AuthRequest("success@gmail.com", "Succ@ss1234");
      invalidEmailRequest = new AuthRequest("invalid", "Succ@ss1234");
      invalidPasswordRequest = new AuthRequest("success@gmail.com", "invalid");
      usedEmailRequest = new AuthRequest("usedemail@gmail.com", "Used@ss1234");
    }

    @Test
    void shouldRegisterSuccessfully() {
      when(userRepository.findByEmail(successRequest.email())).thenReturn(Optional.empty());
      when(passwordEncoder.encode(successRequest.password())).thenReturn("encodedPassword");

      authService.register(successRequest);

      verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void shouldReturnInvalidDataBecauseOfEmail() {
      BookstoreException exception = assertThrows(BookstoreException.class,
          () -> authService.register(invalidEmailRequest));

      assertEquals(ErrorMessages.INVALID_DATA, exception.getMessage());
      assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }

    @Test
    void shouldReturnInvalidDataBecauseOfPassword() {
      BookstoreException exception = assertThrows(BookstoreException.class,
          () -> authService.register(invalidPasswordRequest));

      assertEquals(ErrorMessages.INVALID_DATA, exception.getMessage());
      assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }

    @Test
    void shouldReturnEmailAlreadyUsed() {
      when(userRepository.findByEmail(usedEmailRequest.email())).thenReturn(
          Optional.of(new User()));

      BookstoreException exception = assertThrows(BookstoreException.class,
          () -> authService.register(usedEmailRequest));

      assertEquals(ErrorMessages.EMAIL_USED, exception.getMessage());
      assertEquals(HttpStatus.CONFLICT, exception.getStatusCode());
    }

  }

}