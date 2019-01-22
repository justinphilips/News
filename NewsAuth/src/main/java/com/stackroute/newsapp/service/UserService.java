package com.stackroute.newsapp.service;

import com.stackroute.newsapp.domain.User;
import com.stackroute.newsapp.exception.UserAlreadyExistException;
import com.stackroute.newsapp.exception.UserNotFoundException;

public interface UserService {
  /**
   * Desc: To store the user in database
   *
   * @param user - object need to be stored
   * @return false - failure true - success
   * @throws UserAlreadyExistException
   *
   */
  boolean saveUser(User user) throws UserAlreadyExistException;

  /**
   * Desc: To get the user from database for given userId and password
   *
   * @param userId - User id
   * @param password - Password
   * @return false - failure true - success
   * @throws UserNotFoundException
   */
  User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException;
}
