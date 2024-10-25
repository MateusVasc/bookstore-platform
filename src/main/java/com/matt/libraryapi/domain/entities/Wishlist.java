package com.matt.libraryapi.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "wishlists")
public class Wishlist {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "wishlist_id")
  private UUID id;

  @Column(nullable = false)
  private String name;

  @Column(name = "created_at", nullable = false)
  private LocalDateTime createdAt;

  private User user;

  private List<Book> books;
}
