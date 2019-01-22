package com.stackroute.newsapp.exception;
@SuppressWarnings("serial")
public class NewsNotFoundException extends Exception {

  String message;
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public NewsNotFoundException(String message) {
    super(message);
    this.message = message;
  }

  @Override
  public String toString() {
    return "NewsNotFoundException{" +
      "message='" + message + '\'' +
      '}';
  }
}
