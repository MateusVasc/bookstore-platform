package com.matt.libraryapi.domain.responses;

import com.matt.libraryapi.domain.entities.User;
import java.util.UUID;

public record RegisterResponse(UUID id, String email, String password) {

  public RegisterResponse(User user) {
    this(user.getId(), user.getEmail(), user.getPassword());
  }

}
