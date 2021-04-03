package com.redislabs.edu.redi2read.boot;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.redislabs.edu.redi2read.models.Role;
import com.redislabs.edu.redi2read.models.User;
import com.redislabs.edu.redi2read.repositories.RoleRepository;
import com.redislabs.edu.redi2read.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Order(2)
@Slf4j
public class CreateUsers implements CommandLineRunner {

  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private BCryptPasswordEncoder passwordEncoder;

  @Override
  public void run(String... args) throws Exception {
    if (userRepository.count() == 0) {
      // load the roles
      Role admin = roleRepository.findFirstByName("admin");
      Role customer = roleRepository.findFirstByName("customer");

      try {
        // create a Jackson object mapper
        ObjectMapper mapper = new ObjectMapper();
        // create a type definition to convert the array of JSON into a List of Users
        TypeReference<List<User>> typeReference = new TypeReference<List<User>>() {
        };
        // make the JSON data available as an input stream
        InputStream inputStream = getClass().getResourceAsStream("/data/users/users.json");
        // conver the JSON to objects
        List<User> users = mapper.readValue(inputStream, typeReference);

        users.stream().forEach((user) -> {
          user.setPassword(passwordEncoder.encode(user.getPassword()));
          user.addRole(customer);
          userRepository.save(user);
        });
        log.info(">>>> " + users.size() + " Users Saved!");
      } catch (IOException e) {
        log.info(">>>> Unable to import users: " + e.getMessage());
      }

      User adminUser = new User();
      adminUser.setName("Adminus Admistradore");
      adminUser.setEmail("admin@example.com");
      adminUser.setPassword(passwordEncoder.encode("Reindeer Flotilla"));//
      adminUser.addRole(admin);

      userRepository.save(adminUser);
      log.info(">>>> Loaded User Data and Created users...");
    }
  }
}

