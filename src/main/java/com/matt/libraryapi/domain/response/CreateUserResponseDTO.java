package com.matt.libraryapi.domain.response;

import com.matt.libraryapi.domain.enums.UserGender;

public record CreateUserResponseDTO(String name, String lastname, UserGender gender, int age,
                                    String email, String cpf, boolean isRenting,
                                    boolean isMissingReturnDate, boolean isBlocked) {

}
