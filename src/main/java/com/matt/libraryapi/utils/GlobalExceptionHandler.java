package com.matt.libraryapi.utils;

import com.matt.libraryapi.domain.responses.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(BookstoreException.class)
  public ResponseEntity<ErrorResponse> handleException(BookstoreException e) {
    return ResponseEntity.status(e.getStatusCode()).body(new ErrorResponse(e));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleException(Exception e) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(new ErrorResponse(e));
  }

}
