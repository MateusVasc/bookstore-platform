package com.matt.libraryapi.domain.request;

import com.matt.libraryapi.domain.enums.UserGender;

public record CreateUserRequestDTO(String name, String lastname, UserGender gender, int age,
                                   String email, String cpf, String password) {

}
