package com.matt.libraryapi.repository;

import com.matt.libraryapi.domain.entity.User;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, UUID> {

  User findByCpf(String cpf);

  UserDetails findByEmail(String email);
}
