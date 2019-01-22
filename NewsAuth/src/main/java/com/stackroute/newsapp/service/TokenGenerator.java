package com.stackroute.newsapp.service;

import com.stackroute.newsapp.domain.User;

import java.util.Map;

public interface TokenGenerator {

  Map<String, String> generatorToken(User user);
}
