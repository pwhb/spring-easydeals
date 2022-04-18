package com.loserclub.easydeals.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.loserclub.easydeals.exception.ResourceNotFoundException;
import com.loserclub.easydeals.model.User;
import com.loserclub.easydeals.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UserController {
  @Autowired
  private UserRepository userRepository;

  @GetMapping("/users")
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  @GetMapping("/users/{id}")
  public ResponseEntity<User> getUserById(@PathVariable Long id) throws ResourceNotFoundException {

    User user = userRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));
    return ResponseEntity.ok().body(user);

  }

  @PostMapping("/users")
  public User createUser(@RequestBody User user) {
    return userRepository.save(user);
  }

  @PutMapping("/users/{id}")
  public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User newUser)
      throws ResourceNotFoundException {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));
    user.setEmail(newUser.getEmail());
    user.setFirstName(newUser.getFirstName());
    user.setLastName(newUser.getLastName());
    User updatedUser = userRepository.save(user);
    return ResponseEntity.ok(updatedUser);
  }

  @DeleteMapping("/users/{id}")
  public Map<String, Boolean> deleteEmployee(@PathVariable Long id) throws ResourceNotFoundException {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));
    userRepository.delete(user);
    Map<String, Boolean> response = new HashMap<>();
    response.put("deleted", Boolean.TRUE);
    return response;
  }
}
