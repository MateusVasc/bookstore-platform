package com.matt.libraryapi.domain.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Language {

  ENGLISH("English"),
  SPANISH("Spanish"),
  FRENCH("French"),
  GERMAN("German"),
  CHINESE("Chinese"),
  JAPANESE("Japanese"),
  KOREAN("Korean"),
  ITALIAN("Italian"),
  PORTUGUESE("Portuguese"),
  RUSSIAN("Russian"),
  ARABIC("Arabic"),
  HINDI("Hindi"),
  BENGALI("Bengali"),
  TURKISH("Turkish"),
  DUTCH("Dutch");

  private final String value;

  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return value;
  }
}
