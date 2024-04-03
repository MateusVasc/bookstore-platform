package com.matt.libraryapi.domain.entity;

import com.matt.libraryapi.domain.enums.UserGender;
import com.matt.libraryapi.domain.request.CreateUserRequestDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "user_id")
  private UUID id;

  @Column(length = 25, nullable = false)
  private String name;

  @Column(length = 30, nullable = false)
  private String lastname;

  @Column(length = 30, nullable = false)
  private UserGender gender;

  @Column(nullable = false)
  private int age;

  @Column(length = 50, nullable = false, unique = true)
  private String email;

  @Column(length = 15, nullable = false, unique = true)
  private String cpf;

  @Column(length = 20, nullable = false)
  private String password;

  @OneToMany(mappedBy = "user")
  private List<Loan> loans;

  @Column(nullable = false)
  private boolean isRenting;

  @Column(nullable = false)
  private boolean isMissingReturnDate;

  @Column(nullable = false)
  private boolean isBlocked;

  public User(CreateUserRequestDTO createUserRequestDTO) {
    this.name = createUserRequestDTO.name();
    this.lastname = createUserRequestDTO.lastname();
    this.gender = createUserRequestDTO.gender();
    this.age = createUserRequestDTO.age();
    this.email = createUserRequestDTO.email();
    this.cpf = createUserRequestDTO.cpf();
    this.password = createUserRequestDTO.password();
  }
}
