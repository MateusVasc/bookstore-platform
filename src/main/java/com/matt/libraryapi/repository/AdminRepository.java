package com.matt.libraryapi.repository;

import com.matt.libraryapi.domain.entity.Admin;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface AdminRepository extends JpaRepository<Admin, UUID> {

  UserDetails findByEmail(String email);
}
