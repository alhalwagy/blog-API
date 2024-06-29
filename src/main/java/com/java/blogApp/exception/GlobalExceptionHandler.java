package com.java.blogApp.exception;

import com.java.blogApp.exception.customExceptions.NotAuthToSeeResourseException;
import com.java.blogApp.exception.customExceptions.RecordNotFoundException;
import com.java.blogApp.dto.error.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.Timestamp;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler
  public ResponseEntity<ResponseError> handleRecordNotFoundException(
      RecordNotFoundException recordNotFoundException) {

    ResponseError responseError =
        ResponseError.builder()
            .message(recordNotFoundException.getMessage())
            .status(HttpStatus.NOT_FOUND)
            .occurredOn(new Timestamp(System.currentTimeMillis()))
            .build();
    return new ResponseEntity<>(responseError, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler
  public ResponseEntity<ResponseError> handleNotAuthorizedException(
      NotAuthToSeeResourseException notAuthToSeeResourseException) {
    ResponseError responseError =
        ResponseError.builder()
            .message(notAuthToSeeResourseException.getMessage())
            .status(HttpStatus.UNAUTHORIZED)
            .occurredOn(new Timestamp(System.currentTimeMillis()))
            .build();

    return new ResponseEntity<>(responseError, HttpStatus.UNAUTHORIZED);
  }
}
