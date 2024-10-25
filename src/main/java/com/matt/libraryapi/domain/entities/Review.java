package com.matt.libraryapi.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
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
@Table(name = "reviews")
public class Review {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "review_id")
  private UUID id;

  @Column(nullable = false)
  private String content;

  @Column(name = "rating_quantity", nullable = false)
  private int ratingQuantity;

  @Column(name = "rating_mean", nullable = false)
  private double ratingMean;

  @Column(name = "created_at", nullable = false)
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  @Column(name = "helpful_votes_quantity", nullable = false)
  private int helpfulVotesQuantity;

  private User user;

  private Book book;
}
