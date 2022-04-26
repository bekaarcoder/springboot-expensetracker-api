package com.springboot.expenseapi.controller;

import com.springboot.expenseapi.entity.User;
import com.springboot.expenseapi.entity.UserModel;
import com.springboot.expenseapi.entity.UserUpdateModel;
import com.springboot.expenseapi.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<User> getUser() {
        return new ResponseEntity<>(userService.getUser(), HttpStatus.OK);
    }

    @PutMapping("/users")
    public ResponseEntity<User> updateUser(@Valid @RequestBody UserUpdateModel userModel) {
        return new ResponseEntity<>(userService.updateUser(userModel), HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/users")
    public void deleteUser() {
        userService.deleteUser();
    }
}
