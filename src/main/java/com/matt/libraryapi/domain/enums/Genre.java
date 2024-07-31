package com.matt.libraryapi.domain.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Genre {

  FICTION("Fiction"),
  MYSTERY("Mystery"),
  FANTASY("Fantasy"),
  ROMANCE("Romance"),
  SCIENCE_FICTION("Science Fiction"),
  HORROR("Horror"),
  BIOGRAPHY("Biography"),
  HISTORY("History"),
  POETRY("Poetry"),
  SELF_HELP("Self Help"),
  GRAPHIC_NOVEL("Graphic Novel"),
  CLASSICS("Classics"),
  ADVENTURE("Adventure"),
  YOUNG_ADULT("Young Adult");

  private final String value;

  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return value;
  }
}
