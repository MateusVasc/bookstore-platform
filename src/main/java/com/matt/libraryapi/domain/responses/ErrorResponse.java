package com.matt.libraryapi.domain.responses;

import com.matt.libraryapi.utils.BookstoreException;

public record ErrorResponse(String message, String source) {

  public ErrorResponse(BookstoreException e) {
    this(e.getMessage(), e.getCause() != null ? e.getCause().toString() : "");
  }

  public ErrorResponse(Exception e) {
    this(e.getMessage(), e.getCause() != null ? e.getCause().toString() : "");
  }

}
