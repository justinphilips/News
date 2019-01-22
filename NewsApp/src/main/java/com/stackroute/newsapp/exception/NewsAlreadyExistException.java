package com.stackroute.newsapp.exception;
@SuppressWarnings("serial")
public class NewsAlreadyExistException extends Exception {


  String message;
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public NewsAlreadyExistException(String message) {
    super(message);
    this.message = message;
  }

  @Override
  public String toString() {
    return "NewsAlreadyExistException{" +
      "message='" + message + '\'' +
      '}';
  }
}
