package com.matt.libraryapi.services;

import com.matt.libraryapi.domain.entities.User;
import com.matt.libraryapi.domain.enums.Role;
import com.matt.libraryapi.domain.requests.AuthRequest;
import com.matt.libraryapi.domain.responses.LoginResponse;
import com.matt.libraryapi.infra.JwtToken;
import com.matt.libraryapi.repository.UserRepository;
import com.matt.libraryapi.utils.BookstoreException;
import com.matt.libraryapi.utils.ErrorMessages;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;
  private final JwtToken jwtToken;

  public LoginResponse login(AuthRequest request) {
    User user = userRepository.findByEmail(request.email())
        .orElseThrow(
            () -> new BookstoreException(ErrorMessages.EMAIL_NOT_REGISTERED, HttpStatus.NOT_FOUND));

    if (!passwordEncoder.matches(request.password(), user.getPassword())) {
      throw new BookstoreException(ErrorMessages.INCORRECT_PASSWORD, HttpStatus.UNAUTHORIZED);
    }

    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.email(), request.password()));

    String token = jwtToken.generateToken(user);
    return new LoginResponse(user, token);
  }

  public void register(AuthRequest request) {
    if (!isUserDataValid(request)) {
      throw new BookstoreException(ErrorMessages.INVALID_DATA, HttpStatus.BAD_REQUEST);
    }

    if (userRepository.findByEmail(request.email()).isPresent()) {
      throw new BookstoreException(ErrorMessages.EMAIL_USED, HttpStatus.CONFLICT);
    }

    User newUser = new User(request);
    newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

    userRepository.save(newUser);
  }

  private boolean isUserDataValid(AuthRequest request) {
    return isUserEmailValid(request.email()) && isUserPasswordValid(request.password());
  }

  private boolean isUserEmailValid(String email) {
    if (email == null || email.trim().isEmpty()) {
      return false;
    }

    String emailRegex = "^[^@]{5,}@gmail\\.com$";
    Pattern pattern = Pattern.compile(emailRegex);
    Matcher matcher = pattern.matcher(email);

    return matcher.matches();
  }

  private boolean isUserPasswordValid(String password) {
    if (password == null || password.trim().isEmpty()) {
      return false;
    }

    String senhaRegex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";
    Pattern pattern = Pattern.compile(senhaRegex);
    Matcher matcher = pattern.matcher(password);

    return matcher.matches();
  }

}
