package com.matt.libraryapi.controllers;

import com.matt.libraryapi.domain.requests.SaveBookRequest;
import com.matt.libraryapi.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bookstore/admin")
public class AdminController {

  private final AdminService adminService;

  @PostMapping("/new-book")
  public ResponseEntity<Void> saveBook(@RequestBody SaveBookRequest request) {
    this.adminService.saveBook(request);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

}
