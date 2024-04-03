package com.matt.libraryapi.service;

import com.matt.libraryapi.domain.entity.User;
import com.matt.libraryapi.domain.enums.UserGender;
import com.matt.libraryapi.domain.request.CreateUserRequestDTO;
import com.matt.libraryapi.domain.response.CreateUserResponseDTO;
import com.matt.libraryapi.repository.UserRepository;
import com.matt.libraryapi.utils.LibraryException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public CreateUserResponseDTO createUser(CreateUserRequestDTO createUserRequestDTO)
      throws LibraryException {
    areUserFieldsValid(createUserRequestDTO);

    User userToSave = new User(createUserRequestDTO);
    userToSave.setRenting(false);
    userToSave.setMissingReturnDate(false);
    userToSave.setBlocked(false);

    this.userRepository.save(userToSave);

    User savedUser = this.userRepository.findByCpf(userToSave.getCpf());

    return new CreateUserResponseDTO(savedUser.getName(), savedUser.getLastname(),
        savedUser.getGender(), savedUser.getAge(), savedUser.getEmail(),
        savedUser.getCpf(), savedUser.isRenting(), savedUser.isMissingReturnDate(),
        savedUser.isBlocked());
  }

  private void areUserFieldsValid(CreateUserRequestDTO createUserRequestDTO)
      throws LibraryException {
    if (!isUserNameValid(createUserRequestDTO.name()) && isUserLastnameValid(
        createUserRequestDTO.lastname()) && isUserGenderValid(createUserRequestDTO.gender())
        && isUserAgeValid(createUserRequestDTO.age()) && isUserEmailValid(
        createUserRequestDTO.email()) && isUserCpfValid(createUserRequestDTO.cpf())
        && isUserPasswordValid(
        createUserRequestDTO.password())) {
      throw new LibraryException("Please fill in the fields with valid information");
    }
  }

  private boolean isUserNameValid(String name) {
    return true;
  }

  private boolean isUserLastnameValid(String lastname) {
    return true;
  }

  private boolean isUserGenderValid(UserGender gender) {
    return true;
  }

  private boolean isUserAgeValid(int age) {
    return true;
  }

  private boolean isUserEmailValid(String email) {
    return true;
  }

  private boolean isUserCpfValid(String cpf) {
    return true;
  }

  private boolean isUserPasswordValid(String password) {
    return true;
  }
}
