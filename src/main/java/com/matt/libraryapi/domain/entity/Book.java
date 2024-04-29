package com.matt.libraryapi.domain.entity;

import com.matt.libraryapi.domain.enums.BookGender;
import com.matt.libraryapi.domain.request.AddBookRequestDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "books")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "book_id")
  private UUID id;

  @Column(length = 25, nullable = false)
  private String title;

  @Column(length = 50, nullable = false)
  private String author;

  @Column(nullable = false)
  private String synopsis;

  @Column(length = 30, nullable = false)
  private BookGender gender;

  @Column(name = "publishing_company", length = 50, nullable = false)
  private String publishingCompany;

  @Column(nullable = false)
  private int length;

  @Column(name = "publication_date", nullable = false)
  private Date publicationDate;

  @Column(name = "is_rented", nullable = false)
  private boolean isRented;

  public Book(AddBookRequestDTO addBookRequestDTO) {
    this.title = addBookRequestDTO.title();
    this.author = addBookRequestDTO.author();
    this.synopsis = addBookRequestDTO.synopsis();
    this.gender = addBookRequestDTO.gender();
    this.publishingCompany = addBookRequestDTO.publishingCompany();
    this.length = addBookRequestDTO.length();
    this.publicationDate = addBookRequestDTO.publicationDate();
  }
}
