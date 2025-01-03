package com.restaurant.plazoleta.infraestructure.input.rest;

import com.restaurant.plazoleta.application.dto.UserRequest;
import com.restaurant.plazoleta.application.dto.UserResponse;
import com.restaurant.plazoleta.application.handler.IUserHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UserController {
    private final IUserHandler userHandler;

    @PostMapping
    public ResponseEntity<UserResponse> saveUser(@RequestBody UserRequest userRequest) {
        UserResponse userResponse = userHandler.saveUser(userRequest);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Long> getUserById(@PathVariable("id") Long id){
    Long userResponse = userHandler.getUserById(id);
    return ResponseEntity.ok(userResponse);
    }
}