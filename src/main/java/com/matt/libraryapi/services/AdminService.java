package com.matt.libraryapi.services;

import com.matt.libraryapi.controllers.AdminController;
import com.matt.libraryapi.domain.entities.Book;
import com.matt.libraryapi.domain.requests.SaveBookRequest;
import com.matt.libraryapi.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

  private final AdminController adminController;
  private final BookRepository bookRepository;

  public void saveBook(SaveBookRequest request) {
    Book book = new Book(request);
    bookRepository.save(book);
  }

  public void deleteBook() {}

}
