package com.stackroute.newsapp.exception;

@SuppressWarnings("serial")
public class UserNotFoundException extends Exception  {
  private String message;
  /**
   * Constructor
   *
   * @param message
   */
  public UserNotFoundException(final String message) {
    super();
    this.message = message;
  }
}
