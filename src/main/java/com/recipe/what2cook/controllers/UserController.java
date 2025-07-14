package com.recipe.what2cook.controllers;
import com.recipe.what2cook.dto.UserDTO;
import com.recipe.what2cook.exception.UserNotFoundException;
import com.recipe.what2cook.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    private static final String USER_NOT_FOUND="User not found";

    @GetMapping(path = "/userDetail/{name}", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> displayUserDetail(@PathVariable String name) {
        UserDTO returnVal = null;
        try {
            returnVal = userService.getUser(name);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(404).body(USER_NOT_FOUND);
        }
        return ResponseEntity.ok(returnVal);
    }

    @GetMapping(path = "/allUser", produces = "application/json")
    public ResponseEntity<List<UserDTO>> displayAllUser() {
        return ResponseEntity.ok(userService.getAllUser());
    }

    @PostMapping("/users")
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO) {
        // Logic to save the user
        UserDTO savedUser = userService.addUser(userDTO);
        return ResponseEntity.ok(savedUser);
    }
    @PutMapping("/users")
    public ResponseEntity<Object> updateUser(@RequestBody UserDTO userDTO) {
        // Logic to update the user
        UserDTO savedUser = null;
        try {
            savedUser = userService.updateUser(userDTO);
        } catch (UserNotFoundException e) {
            log.error(USER_NOT_FOUND, e);
            return ResponseEntity.status(404).body(USER_NOT_FOUND);
        }
        return ResponseEntity.ok(savedUser);
    }

}
