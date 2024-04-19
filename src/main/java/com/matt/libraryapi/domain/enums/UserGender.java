package com.matt.libraryapi.domain.enums;

public enum UserGender {
  MALE("male"),
  FEMALE("female");

  private String gender;

  UserGender(String gender) {
    this.gender = gender;
  }

  public String getGender() {
    return this.gender;
  }
}
