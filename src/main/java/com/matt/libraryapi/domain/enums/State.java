package com.matt.libraryapi.domain.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum State {

  IN_STOCK("In stock"),
  SOLD_OUT("Sold out");

  private final String value;

  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return value;
  }
}
