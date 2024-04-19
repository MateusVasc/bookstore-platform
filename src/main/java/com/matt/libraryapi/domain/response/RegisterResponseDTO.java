package com.matt.libraryapi.domain.response;

import com.matt.libraryapi.domain.enums.Role;
import com.matt.libraryapi.domain.enums.UserGender;

public record RegisterResponseDTO(String name, String lastname, UserGender gender, String email,
                                  String cpf, boolean isRenting, boolean isMissingReturnDate,
                                  boolean isBlocked, Role role) {

}
