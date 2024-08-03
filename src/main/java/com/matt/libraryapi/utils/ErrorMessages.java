package com.matt.libraryapi.utils;

public class ErrorMessages {

  public static final String INCORRECT_PASSWORD = "Password is incorrect";
  public static final String EMAIL_NOT_REGISTERED = "Email not registered";
  public static final String EMAIL_USED = "Email already used";
  public static final String INVALID_DATA = "Invalid data";
  public static final String UNAUTHORIZED_USER = "Only an admin user can make this action";

  private ErrorMessages() {
    throw new UnsupportedOperationException(
        "This is a utility class and should not be instanciated");
  }

}
