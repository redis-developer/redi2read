package com.redislabs.edu.redi2read.controllers;

import com.redislabs.edu.redi2read.models.User;
import com.redislabs.edu.redi2read.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

  @Autowired
  private UserRepository userRepository;

  @GetMapping
  public Iterable<User> all() {
    return userRepository.findAll();
  }
}
