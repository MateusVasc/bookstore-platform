package com.matt.libraryapi.repository;

import com.matt.libraryapi.domain.entity.User;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, UUID> {

  User findByCpf(String cpf);
}
