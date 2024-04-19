package com.matt.libraryapi.domain.entity;

import com.matt.libraryapi.domain.enums.Role;
import com.matt.libraryapi.domain.enums.UserGender;
import com.matt.libraryapi.domain.request.RegisterRequestDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

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

  @Column(length = 50, nullable = false, unique = true)
  private String email;

  @Column(length = 15, nullable = false, unique = true)
  private String cpf;

  @Column(nullable = false)
  private String password;

  @OneToMany(mappedBy = "user")
  private List<Loan> loans;

  @Column(nullable = false)
  private boolean isRenting;

  @Column(nullable = false)
  private boolean isMissingReturnDate;

  @Column(nullable = false)
  private boolean isBlocked;

  @Column(nullable = false)
  private Role role;

  public User(RegisterRequestDTO registerRequestDTO) {
    this.name = registerRequestDTO.name();
    this.lastname = registerRequestDTO.lastname();
    this.gender = registerRequestDTO.gender();
    this.email = registerRequestDTO.email();
    this.cpf = registerRequestDTO.cpf();
    this.password = registerRequestDTO.password();
    this.role = registerRequestDTO.role();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    if (this.role == Role.ADMIN) {
      return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
    } else {
      return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }
  }

  @Override
  public String getUsername() {
    return this.getEmail();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
