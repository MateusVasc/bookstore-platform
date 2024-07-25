package com.matt.libraryapi.controllers;

import com.matt.libraryapi.domain.requests.AuthRequest;
import com.matt.libraryapi.domain.responses.LoginResponse;
import com.matt.libraryapi.domain.responses.RegisterResponse;
import com.matt.libraryapi.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookstore/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  @PostMapping("/login")
  public ResponseEntity<LoginResponse> login(@RequestBody AuthRequest request) {
    return ResponseEntity.status(HttpStatus.OK).body(this.authService.login(request));
  }

  @PostMapping("/register")
  public ResponseEntity<RegisterResponse> register(@RequestBody AuthRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(this.authService.register(request));
  }

}
