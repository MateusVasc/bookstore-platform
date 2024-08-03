package com.matt.libraryapi.services;

import com.matt.libraryapi.controllers.AdminController;
import com.matt.libraryapi.domain.entities.Book;
import com.matt.libraryapi.domain.entities.User;
import com.matt.libraryapi.domain.enums.Genre;
import com.matt.libraryapi.domain.enums.Language;
import com.matt.libraryapi.domain.enums.Role;
import com.matt.libraryapi.domain.enums.State;
import com.matt.libraryapi.domain.requests.SaveBookRequest;
import com.matt.libraryapi.repository.BookRepository;
import com.matt.libraryapi.repository.UserRepository;
import com.matt.libraryapi.utils.BookstoreException;
import com.matt.libraryapi.utils.ErrorMessages;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

  private final AdminController adminController;
  private final BookRepository bookRepository;
  private final UserRepository userRepository;

  public void saveBook(UUID adminId, SaveBookRequest request) {
    Optional<User> admin = userRepository.findById(adminId);

    if (admin.isEmpty()) {
      throw new BookstoreException(ErrorMessages.EMAIL_NOT_REGISTERED, HttpStatus.NOT_FOUND);
    }

    if (!admin.get().getRole().equals(Role.ADMIN.getValue())) {
      throw new BookstoreException(ErrorMessages.UNAUTHORIZED_USER, HttpStatus.UNAUTHORIZED);
    }

    if (!isBookDataValid(request)) {
      throw new BookstoreException(ErrorMessages.INVALID_DATA, HttpStatus.BAD_REQUEST);
    }

    Book book = new Book(request);
    bookRepository.save(book);
  }

  public void deleteBook() {}

  private boolean isBookDataValid(SaveBookRequest request) {
    return isBookTitleValid(request.title()) &&
        isBookAuthorValid(request.author()) &&
        isBookPublisherValid(request.publisher()) &&
        isBookPublishedDateValid(request.publishedAt()) &&
        isBookGenreValid(request.genre()) &&
        isBookLanguageValid(request.language()) &&
        isBookPagesValid(request.numPages()) &&
        isBookCoverValid(request.cover()) &&
        isBookPriceValid(request.price()) &&
        isBookStateValid(request.availability()) &&
        isBookStockCountValid(request.stockCount());
  }

  private boolean isBookTitleValid(String title) {
    return !title.trim().equals(" ");
  }
  private boolean isBookAuthorValid(String author) {
    return !author.trim().equals(" ");
  }
  private boolean isBookPublisherValid(String publisher) {
    return !publisher.trim().equals(" ");
  }
  private boolean isBookPublishedDateValid(LocalDateTime publishedAt) {
    return true;
  }
  private boolean isBookGenreValid(Genre genre) {
    for (Genre g : Genre.values()) {
      if (g.equals(genre)) {
        return true;
      }
    }

    return false;
  }
  private boolean isBookLanguageValid(Language language) {
    for (Language l : Language.values()) {
      if (l.equals(language)) {
        return true;
      }
    }

    return false;
  }
  private boolean isBookPagesValid(int numPages) {
    return true;
  }
  private boolean isBookCoverValid(byte[] cover) {
    return true;
  }
  private boolean isBookPriceValid(double price) {
    return true;
  }
  private boolean isBookStateValid(State state) {
    for (State s : State.values()) {
      if (s.equals(state)) {
        return true;
      }
    }

    return false;
  }
  private boolean isBookStockCountValid(int stockCount) {
    return true;
  }

}
