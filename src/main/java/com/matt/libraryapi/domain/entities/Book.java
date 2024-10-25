package com.matt.libraryapi.domain.entities;

import com.matt.libraryapi.domain.enums.Format;
import com.matt.libraryapi.domain.enums.Genre;
import com.matt.libraryapi.domain.enums.Language;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "books")
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "book_id")
  private UUID id;

  @Column(nullable = false, unique = true)
  private String title;

  @Column(nullable = false)
  private String author;

  @Column(nullable = false)
  private String description;

  @Column(nullable = false)
  private String isbn;

  @Column(nullable = false)
  private String publisher;

  @Column(name = "published_at", nullable = false)
  private LocalDateTime publishedAt;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private Genre genre;

  @Column(nullable = false)
  private double price;

  @Column(name = "stock_quantity", nullable = false)
  private int stockQuantity;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private Language language;

  @Column(name = "page_count", nullable = false)
  private int pageCount;

  @Column(name = "cover_url", nullable = true)
  private String coverUrl;

  @Column(nullable = false)
  private double ratings;

  @Column(name = "created_at", nullable = false)
  private LocalDateTime createdAt;

  @Column(name = "updated_at", nullable = false)
  private LocalDateTime updatedAt;

  private Format format;

  private List<Review> reviews;

  private List<Wishlist> wishlists;

  private List<Cart> carts;

  private List<Purchase> purchases;
}
