package com.microservicio.restaurant.infraestructure.input.rest;

import com.microservicio.restaurant.application.dto.RestaurantRequest;
import com.microservicio.restaurant.application.dto.RestaurantResponse;
import com.microservicio.restaurant.application.handler.IRestaurantHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurantes")
@RequiredArgsConstructor
public class RestaurantController {

    private final IRestaurantHandler restaurantHandler;

    @PostMapping
    public ResponseEntity<RestaurantResponse> saveRestaurant(@RequestBody RestaurantRequest restaurantRequest) {
        RestaurantResponse restaurantResponse = restaurantHandler.saveRestaurant(restaurantRequest);
        return new ResponseEntity<>(restaurantResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RestaurantResponse>> getAllRestaurants() {
        List<RestaurantResponse> restaurantResponses = restaurantHandler.getAllRestaurants();
        return new ResponseEntity<>(restaurantResponses, HttpStatus.OK);
    }
}
