package com.matt.libraryapi.domain.request;

import com.matt.libraryapi.domain.enums.Role;
import com.matt.libraryapi.domain.enums.UserGender;

public record RegisterRequestDTO(String name, String lastname, UserGender gender, String email,
                                 String cpf, String password, Role role) {

}
