package com.matt.libraryapi.controller;

import com.matt.libraryapi.domain.enums.Role;
import com.matt.libraryapi.domain.request.LoginAuthenticationRequestDTO;
import com.matt.libraryapi.domain.request.RegisterRequestDTO;
import com.matt.libraryapi.repository.AdminRepository;
import com.matt.libraryapi.repository.UserRepository;
import com.matt.libraryapi.service.AuthenticationService;
import com.matt.libraryapi.utils.LibraryException;
import com.matt.libraryapi.utils.MessageUtil;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

  private final AuthenticationService authenticationService;

  public AuthenticationController(AuthenticationManager authenticationManager,
      UserRepository userRepository, AdminRepository adminRepository,
      AuthenticationService authenticationService) {
    this.authenticationService = authenticationService;
  }

  @PostMapping("/login")
  public ResponseEntity<Object> login(@RequestBody @Valid LoginAuthenticationRequestDTO loginData) {
    ResponseEntity<Object> response;

    try {
      response = ResponseEntity.ok(this.authenticationService.login(loginData));

    } catch (LibraryException exception) {
      response = ResponseEntity.badRequest().body(new MessageUtil(exception.getMessage()));
    }

    return response;
  }

  @PostMapping("/register")
  public ResponseEntity<Object> register(@RequestBody RegisterRequestDTO request)
      throws LibraryException {
    ResponseEntity<Object> response;

    if (request.role() == Role.USER) {
      try {
        response = ResponseEntity.ok(this.authenticationService.registerUser(request));
      } catch (LibraryException exception) {
        response = ResponseEntity.badRequest().body(new MessageUtil(exception.getMessage()));
      }
    } else {
      response = ResponseEntity.badRequest()
          .body(new MessageUtil("An admin has to be created by another admin"));
    }

    return response;
  }
}
