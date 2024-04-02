package com.matt.libraryapi.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "loans")
public class Loan {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "loan_id")
  private UUID id;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @OneToOne
  @JoinColumn(name = "loaned_book_id")
  private Book loanedBook;

  @Column(name = "loan_start_date", nullable = false)
  private Date loanStart;

  @Column(name = "loan_end_date", nullable = false)
  private Date loanEnd;
}
