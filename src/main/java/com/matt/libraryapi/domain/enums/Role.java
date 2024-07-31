package com.matt.libraryapi.domain.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Role {

  ADMIN("Admin"),
  USER("User");

  private final String value;

  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return value;
  }
}
