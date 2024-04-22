package com.matt.libraryapi.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.matt.libraryapi.domain.entity.User;
import com.matt.libraryapi.utils.LibraryException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

  private static final String SECRET_KEY = System.getenv("SECRET_KEY");

  public String generateToken(User user) throws LibraryException {
    try {
      Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

      return JWT.create()
          .withIssuer("auth-api")
          .withSubject(user.getEmail())
          .withExpiresAt(generateExpirationDate())
          .sign(algorithm);

    } catch (JWTCreationException exception) {
      throw new LibraryException("Error while creating token");
    }
  }

  public String validateToken(String token) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

      return JWT.require(algorithm)
          .withIssuer("auth-api")
          .build()
          .verify(token)
          .getSubject();

    } catch (JWTVerificationException exception) {
      return "";
    }
  }

  private Instant generateExpirationDate() {
    return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
  }
}
