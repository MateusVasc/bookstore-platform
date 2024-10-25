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
@Table(name = "carts")
public class Cart {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "cart_id")
  private UUID id;

  @Column(name = "total_price")
  private double totalPrice;

  @Column(name = "last_updated_at")
  private LocalDateTime lastUpdatedAt;

  private User user;

  private List<Book> books;
}
