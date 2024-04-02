package com.matt.libraryapi.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "admins")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Admin {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "admin_id")
  private UUID id;

  @Column(length = 25, nullable = false)
  private String name;

  @Column(length = 30, nullable = false)
  private String lastname;

  @Column(length = 30, nullable = false)
  private String position;

  @Column(length = 50, nullable = false, unique = true)
  private String email;

  @Column(length = 20, nullable = false)
  private String password;
}
