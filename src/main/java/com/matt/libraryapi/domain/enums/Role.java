package com.matt.libraryapi.domain.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Role {

  ADMIN("Admin"),
  USER("User");

  private final String displayRole;

  public String getRole() {
    return displayRole;
  }

  @Override
  public String toString() {
    return displayRole;
  }
}
