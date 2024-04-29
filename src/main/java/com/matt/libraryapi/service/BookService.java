package com.matt.libraryapi.service;

import com.matt.libraryapi.domain.entity.Book;
import com.matt.libraryapi.domain.enums.BookGender;
import com.matt.libraryapi.domain.request.AddBookRequestDTO;
import com.matt.libraryapi.domain.response.AddBookResponseDTO;
import com.matt.libraryapi.repository.BookRepository;
import com.matt.libraryapi.utils.LibraryException;
import java.util.Date;
import org.springframework.stereotype.Service;

@Service
public class BookService {

  private final BookRepository bookRepository;

  public BookService(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  public AddBookResponseDTO addBook(AddBookRequestDTO request) throws LibraryException {
    this.areBookFieldsValid(request);

    Book bookToSave = new Book(request);
    bookToSave.setRented(false);

    this.bookRepository.save(bookToSave);
    Book response = this.bookRepository.findByTitle(bookToSave.getTitle());

    return new AddBookResponseDTO(response.getTitle(), response.getAuthor(), response.getSynopsis(),
        response.getGender(), response.getPublishingCompany(), response.getLength(),
        response.getPublicationDate(), response.isRented());
  }

  private void areBookFieldsValid(AddBookRequestDTO bookRequest) throws LibraryException {
    if (isBookTitleValid(bookRequest.title()) && isBookAuthorValid(bookRequest.author())
        && isBookSynopsisValid(bookRequest.synopsis()) && isBookGenderValid(bookRequest.gender())
        && isBookPublishingCompanyValid(bookRequest.publishingCompany())
        && isBookPublicationDateValid(bookRequest.publicationDate()) == false) {
      throw new LibraryException("Please inform valid fields");
    }
  }

  private boolean isBookTitleValid(String title) {
    return title != null && !title.trim().isEmpty();
  }

  private boolean isBookAuthorValid(String author) {
    return author != null && !author.trim().isEmpty();
  }

  private boolean isBookSynopsisValid(String synopsis) {
    return synopsis != null && !synopsis.trim().isEmpty();
  }

  private boolean isBookGenderValid(BookGender gender) {
    return gender != null;
  }

  private boolean isBookPublishingCompanyValid(String publishingCompany) {
    return publishingCompany != null && !publishingCompany.trim().isEmpty();
  }

  private boolean isBookPublicationDateValid(Date publicationDate) {
    return publicationDate != null;
  }
}
