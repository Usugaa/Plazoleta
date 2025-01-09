package com.restaurant.plazoleta.infraestructure.input.rest;

import com.restaurant.plazoleta.application.dto.UserRequest;
import com.restaurant.plazoleta.application.dto.UserResponse;
import com.restaurant.plazoleta.application.handler.IUserHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final IUserHandler userHandler;

    @PostMapping("/admin")
    public ResponseEntity<UserResponse> saveAdmin(@RequestBody UserRequest userRequest) {
        UserResponse userResponse = userHandler.saveAdmin(userRequest);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    @PostMapping("/owner")
    public ResponseEntity<UserResponse> saveOwner(@RequestBody UserRequest userRequest) {
        UserResponse userResponse = userHandler.saveOwner(userRequest);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    @PostMapping("/employee")
    public ResponseEntity<UserResponse> saveEmployee(@RequestBody UserRequest userRequest) {
        UserResponse userResponse = userHandler.saveEmployee(userRequest);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    @PostMapping("/client")
    public ResponseEntity<UserResponse> saveClient(@RequestBody UserRequest userRequest) {
        UserResponse userResponse = userHandler.saveClient(userRequest);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Long> getUserById(@PathVariable("id") Long id) {
        Long userResponse = userHandler.getUserById(id);
        return ResponseEntity.ok(userResponse);
    }
}