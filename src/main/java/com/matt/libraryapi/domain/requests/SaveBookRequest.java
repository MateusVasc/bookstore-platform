package com.matt.libraryapi.domain.requests;

import com.matt.libraryapi.domain.enums.Genre;
import com.matt.libraryapi.domain.enums.Language;
import com.matt.libraryapi.domain.enums.State;
import java.time.LocalDateTime;

public record SaveBookRequest(String title, String author, String publisher,
                              LocalDateTime publishedAt, Genre genre, Language language,
                              int numPages, byte[] cover, double price, State availability,
                              int stockCount) {

}
