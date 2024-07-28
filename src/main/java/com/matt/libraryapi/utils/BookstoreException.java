package com.matt.libraryapi.utils;

import org.springframework.http.HttpStatus;

public class BookstoreException extends RuntimeException {

  private final String message;
  private final HttpStatus statusCode;

  public BookstoreException(String message, HttpStatus statusCode) {
    this.message = message;
    this.statusCode = statusCode;
  }

  @Override
  public String getMessage() {
    return message;
  }

  public HttpStatus getStatusCode() {
    return statusCode;
  }
}
