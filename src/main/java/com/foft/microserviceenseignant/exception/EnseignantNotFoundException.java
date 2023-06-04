package com.foft.microserviceenseignant.exception;

 import org.springframework.web.bind.annotation.ResponseStatus;
 import org.springframework.http.HttpStatus;


@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EnseignantNotFoundException extends RuntimeException {
  public EnseignantNotFoundException(String message) {
      super(message);
  }
}