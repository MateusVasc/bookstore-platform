package com.matt.libraryapi.domain.entities;

import com.matt.libraryapi.domain.enums.NewsletterStatus;
import com.matt.libraryapi.domain.enums.Role;
import com.matt.libraryapi.domain.enums.AccountStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "user_id")
  private UUID id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false, unique = true)
  private String username;

  @Email
  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private Role role;

  @Column(name = "account_status", nullable = false)
  @Enumerated(EnumType.STRING)
  private AccountStatus accStatus;

  @Column(name = "account_created_at", nullable = false)
  private LocalDateTime accountCreatedAt;

  @Column(name = "last_logged_at", nullable = false)
  private LocalDateTime lastLoggedAt;

  @Column(name = "news_letter_status", nullable = false)
  @Enumerated(EnumType.STRING)
  private NewsletterStatus newsStatus;

  private List<Wishlist> wishlists;

  private List<Purchase> purchaseHistory;

  private Cart cart;

  private List<Review> reviews;
}
