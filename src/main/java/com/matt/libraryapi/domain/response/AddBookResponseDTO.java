package com.matt.libraryapi.domain.response;

import com.matt.libraryapi.domain.enums.BookGender;
import java.util.Date;

public record AddBookResponseDTO(String title, String author, String synopsis, BookGender gender,
                                 String publishingCompany, int length, Date publicationDate,
                                 boolean isRented) {

}
