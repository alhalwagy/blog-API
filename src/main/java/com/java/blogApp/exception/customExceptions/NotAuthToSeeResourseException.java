package com.java.blogApp.exception.customExceptions;

public class NotAuthToSeeResourseException extends RuntimeException {

  public NotAuthToSeeResourseException(String message) {
    super(message);
  }

  public NotAuthToSeeResourseException(String message, Throwable cause) {
    super(message, cause);
  }

  public NotAuthToSeeResourseException(Throwable cause) {
    super(cause);
  }
}
