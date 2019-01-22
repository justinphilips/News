package com.stackroute.newsapp.service;

import com.stackroute.newsapp.domain.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtTokenGeneratorImplementation implements TokenGenerator {

  @Override
  public Map<String, String> generatorToken(User user) {
    String jwtToken = "";
    Map<String, String> map = new HashMap<String, String>();
    jwtToken = Jwts.builder().setSubject(user.getUserId()).setIssuedAt(new Date())
      .signWith(SignatureAlgorithm.HS256, "secretkey").compact();
    map.put("userId", user.getUserId());
    map.put("token", jwtToken);
    map.put("message", "Successfully loggedin");
    return map;
  }

}

