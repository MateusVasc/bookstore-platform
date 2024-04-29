package com.matt.libraryapi.controller;

import com.matt.libraryapi.domain.request.AddBookRequestDTO;
import com.matt.libraryapi.service.BookService;
import com.matt.libraryapi.utils.LibraryException;
import com.matt.libraryapi.utils.MessageUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/api/books")
public class BookController {

  private final BookService bookService;

  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  @PostMapping("/add")
  public ResponseEntity<Object> addNewBook(AddBookRequestDTO addBookRequestDTO) throws LibraryException {
    ResponseEntity<Object> response;

    try {
      response = ResponseEntity.ok(this.bookService.addBook(addBookRequestDTO));
    } catch (LibraryException e) {
      response = ResponseEntity.badRequest()
          .body(new MessageUtil(e.getMessage()));
    }

    return response;
  }
}
