package com.matt.libraryapi.domain.controller;

import com.matt.libraryapi.domain.request.CreateUserRequestDTO;
import com.matt.libraryapi.service.UserService;
import com.matt.libraryapi.utils.LibraryException;
import com.matt.libraryapi.utils.MessageUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/create")
  public ResponseEntity<Object> createUser(@RequestBody CreateUserRequestDTO createUserRequestDTO) {
    ResponseEntity<Object> response;

    try {
      response = new ResponseEntity<>(userService.createUser(createUserRequestDTO), HttpStatus.CREATED);
    } catch (LibraryException exception) {
      response = new ResponseEntity<>(new MessageUtil(exception.getMessage()), HttpStatus.NOT_ACCEPTABLE);
    }

    return response;
  }
}
