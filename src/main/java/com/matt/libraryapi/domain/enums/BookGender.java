package com.matt.libraryapi.domain.enums;

public enum BookGender {
  ROMANCE("romance"),
  SCIENCE_FICTION("science fiction"),
  FANTASY("fantasy"),
  MYSTERY("mystery"),
  SUSPENSE("suspense"),
  THRILLER("thriller"),
  HORROR("horror"),
  ADVENTURE("adventure"),
  DETECTIVE("detective"),
  DRAMA("drama"),
  PHILOSOPHY("philosophy"),
  BIOGRAPHY("biografy"),
  AUTOBIOGRAPHY("autobiografy"),
  HISTORY("history"),
  POLITICS("politics"),
  SCIENCE("science"),
  TECHNOLOGY("technology"),
  SELF_HELP("self help"),
  GEOGRAPHY("geography"),
  MATHEMATICS("mathematics"),
  PSYCHOLOGY("psychology");

  private String gender;

  BookGender(String gender) {
    this.gender = gender;
  }

  public String getGender() {
    return this.gender;
  }
}
