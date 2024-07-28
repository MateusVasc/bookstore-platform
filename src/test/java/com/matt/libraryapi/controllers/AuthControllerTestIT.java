package com.matt.libraryapi.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.matt.libraryapi.domain.entities.User;
import com.matt.libraryapi.domain.requests.AuthRequest;
import com.matt.libraryapi.domain.responses.LoginResponse;
import com.matt.libraryapi.infra.JwtToken;
import com.matt.libraryapi.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class AuthControllerTestIT {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private JwtToken jwtToken;

  @BeforeEach
  void setUp() {
    userRepository.deleteAll();
  }

  @Nested
  class Login {

    @Test
    @DisplayName("Should login successfully")
    void shouldLoginSuccessfully() throws Exception {
      String email = "testuser@gmail.com";
      String password = "Test@1234";
      String encodedPassword = passwordEncoder.encode(password);

      User user = new User(email, encodedPassword);

      userRepository.save(user);

      AuthRequest request = new AuthRequest(email, password);

      mockMvc.perform(post("/bookstore/auth/login")
              .contentType(MediaType.APPLICATION_JSON)
              .content(objectMapper.writeValueAsString(request)))
          .andExpect(status().isOk())
          .andExpect(content().contentType(MediaType.APPLICATION_JSON))
          .andExpect(content().json(objectMapper.writeValueAsString(
              new LoginResponse(user.getId(), jwtToken.generateToken(user)))));
    }

  }

}