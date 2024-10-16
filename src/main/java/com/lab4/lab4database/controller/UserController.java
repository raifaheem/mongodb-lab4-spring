package com.lab4.lab4database.controller;

import java.util.List;

import com.lab4.lab4database.model.User;
import com.lab4.lab4database.userrepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/add-user")
    public void addUser(@RequestBody User user) {
        userRepository.save(user);
    }

    @GetMapping("/get-users")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/get-user-by-id/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        for (User user : userRepository.findAll()) {
            if (user.getId() == id) {
                return ResponseEntity.status(HttpStatus.OK).body(user);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/update-user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user) {
        for (User u : userRepository.findAll()) {
            if (u.getId() == id) {
                u.setUsername(user.getUsername());
                u.setPassword(user.getPassword());
            }
        }
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @DeleteMapping("/delete-user-by-id/{id}")
    public void deleteUserById(@PathVariable int id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new NullPointerException("User with id " + id + " not found"));
        userRepository.delete(user);
    }

}
