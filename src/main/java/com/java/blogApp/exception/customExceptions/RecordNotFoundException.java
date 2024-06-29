package com.java.blogApp.exception.customExceptions;

public class RecordNotFoundException extends RuntimeException {

  public RecordNotFoundException(String message) {
    super(message);
  }

  public RecordNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  public RecordNotFoundException(Throwable cause) {
    super(cause);
  }
}
