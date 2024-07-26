package com.matt.libraryapi.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import com.matt.libraryapi.domain.entities.User;
import com.matt.libraryapi.domain.enums.Role;
import com.matt.libraryapi.domain.requests.AuthRequest;
import com.matt.libraryapi.domain.responses.LoginResponse;
import com.matt.libraryapi.infra.JwtToken;
import com.matt.libraryapi.repository.UserRepository;
import com.matt.libraryapi.utils.BookstoreException;
import com.matt.libraryapi.utils.ErrorMessages;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
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

    private static AuthRequest successRequest;
    private static AuthRequest unregisteredEmailRequest;
    private static AuthRequest unvalidPasswordRequest;
    private static User user;

    @BeforeAll
    static void setUp() {
      successRequest = new AuthRequest("success@gmail.com", "Succ@ss1234");
      unregisteredEmailRequest = new AuthRequest("invalid", "Succ@ss1234");
      unvalidPasswordRequest = new AuthRequest("success@gmail.com", "invalid");
      user = new User(successRequest);
      user.setId(UUID.randomUUID());
      user.setRole(Role.USER);
    }

    @Test
    @DisplayName("Should login successfully")
    void shouldLoginSuccessfully() {
      when(userRepository.findByEmail(successRequest.email())).thenReturn(
          Optional.ofNullable(user));
      when(passwordEncoder.matches(successRequest.password(), user.getPassword())).thenReturn(true);
      when(jwtToken.generateToken(user)).thenReturn("generatedToken");

      LoginResponse response = authService.login(successRequest);

      assertNotNull(response);
      assertEquals(user.getId(), response.id());
      assertEquals("generatedToken", response.token());
    }

    @Test
    @DisplayName("Should return exception due to unregistered email")
    void shouldReturnUserNotFound() {
      when(userRepository.findByEmail(unregisteredEmailRequest.email())).thenReturn(
          Optional.empty());

      BookstoreException exception = assertThrows(BookstoreException.class,
          () -> authService.login(unregisteredEmailRequest));

      assertEquals(ErrorMessages.EMAIL_NOT_REGISTERED, exception.getMessage());
      assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }

    @Test
    @DisplayName("Should return exception due to invalid password")
    void shouldReturnInvalidPassword() {
      when(userRepository.findByEmail(unvalidPasswordRequest.email())).thenReturn(
          Optional.ofNullable(user));
      when(passwordEncoder.matches(unvalidPasswordRequest.password(),
          user.getPassword())).thenReturn(false);

      BookstoreException exception = assertThrows(BookstoreException.class,
          () -> authService.login(unvalidPasswordRequest));

      assertEquals(ErrorMessages.INCORRECT_PASSWORD, exception.getMessage());
      assertEquals(HttpStatus.UNAUTHORIZED, exception.getStatusCode());
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
    @DisplayName("Should register successfully")
    void shouldRegisterSuccessfully() {
      when(userRepository.findByEmail(successRequest.email())).thenReturn(Optional.empty());
      when(passwordEncoder.encode(successRequest.password())).thenReturn("encodedPassword");

      authService.register(successRequest);

      verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    @DisplayName("Should return exception due to invalid email")
    void shouldReturnInvalidDataBecauseOfEmail() {
      BookstoreException exception = assertThrows(BookstoreException.class,
          () -> authService.register(invalidEmailRequest));

      assertEquals(ErrorMessages.INVALID_DATA, exception.getMessage());
      assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }

    @Test
    @DisplayName("Should return exception due to invalid password")
    void shouldReturnInvalidDataBecauseOfPassword() {
      BookstoreException exception = assertThrows(BookstoreException.class,
          () -> authService.register(invalidPasswordRequest));

      assertEquals(ErrorMessages.INVALID_DATA, exception.getMessage());
      assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }

    @Test
    @DisplayName("Should return exception due to already used email")
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