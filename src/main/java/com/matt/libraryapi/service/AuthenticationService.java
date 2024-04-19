package com.matt.libraryapi.service;

import com.matt.libraryapi.domain.entity.Admin;
import com.matt.libraryapi.domain.entity.User;
import com.matt.libraryapi.domain.request.LoginAuthenticationRequestDTO;
import com.matt.libraryapi.domain.request.RegisterRequestDTO;
import com.matt.libraryapi.domain.response.RegisterResponseDTO;
import com.matt.libraryapi.repository.AdminRepository;
import com.matt.libraryapi.repository.UserRepository;
import com.matt.libraryapi.utils.LibraryException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

  private final AuthenticationManager authenticationManager;
  private final UserRepository userRepository;
  private final AdminRepository adminRepository;

  public AuthenticationService(AuthenticationManager authenticationManager,
      UserRepository userRepository, AdminRepository adminRepository) {
    this.authenticationManager = authenticationManager;
    this.userRepository = userRepository;
    this.adminRepository = adminRepository;
  }

  public void Login(LoginAuthenticationRequestDTO loginData) {
    var userData = new UsernamePasswordAuthenticationToken(loginData.email(), loginData.password());
    var auth = this.authenticationManager.authenticate(userData);
  }

  public RegisterResponseDTO registerUser(RegisterRequestDTO request) throws LibraryException {
    if (this.userRepository.findByEmail(request.email()) != null) {
      throw new LibraryException("User already registered");
    } else {
      String encryptedPassword = new BCryptPasswordEncoder().encode(request.password());

      User userToSave = new User(request);
      userToSave.setPassword(encryptedPassword);
      userToSave.setRenting(false);
      userToSave.setMissingReturnDate(false);
      userToSave.setBlocked(false);

      this.userRepository.save(userToSave);
      User userResponse = (User) this.userRepository.findByEmail(request.email());

      return new RegisterResponseDTO(userResponse.getUsername(), userResponse.getLastname(),
          userResponse.getGender(),
          userResponse.getEmail(), userResponse.getCpf(), userResponse.isRenting(),
          userResponse.isMissingReturnDate(),
          userResponse.isBlocked(), userResponse.getRole());
    }
  }

  public void registerAdmin(RegisterRequestDTO request) throws LibraryException {
    if (this.adminRepository.findByEmail(request.email()) != null) {
      throw new LibraryException("Admin already registered");
    } else {
      String encryptedPassword = new BCryptPasswordEncoder().encode(request.password());

      Admin adminToSave = new Admin(request);
      adminToSave.setPassword(encryptedPassword);

      this.adminRepository.save(adminToSave);
    }
  }
}
