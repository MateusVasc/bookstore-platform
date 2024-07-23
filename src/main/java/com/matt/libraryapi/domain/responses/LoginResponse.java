package com.matt.libraryapi.domain.responses;

import com.matt.libraryapi.domain.entities.User;
import java.util.UUID;

public record LoginResponse(UUID id, String token) {

  public LoginResponse(User user, String token) {
    this(user.getId(), token);
  }

}
