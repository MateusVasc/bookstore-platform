package com.matt.libraryapi.repository;

import com.matt.libraryapi.domain.entity.Book;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, UUID> {

  Book findByTitle(String title);
}
