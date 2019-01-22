package com.stackroute.newsapp.exception;

@SuppressWarnings("serial")
public class UserAlreadyExistException extends Throwable{
  private String message;

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  /**
   * Constructor
   *
   * @param message
   */
  public UserAlreadyExistException(final String message) {
    super();
    this.message = message;
  }

  @Override
  public String toString() {
    return "UserAlreadyExistException [message=" + message + "]";
  }
}
