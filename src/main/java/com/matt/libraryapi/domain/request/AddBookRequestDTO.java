package com.matt.libraryapi.domain.request;

import com.matt.libraryapi.domain.enums.BookGender;
import java.util.Date;

public record AddBookRequestDTO(String title, String author, String synopsis, BookGender gender,
                                String publishingCompany, int length, Date publicationDate) {

}
