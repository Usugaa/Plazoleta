package com.microservicio.restaurant.infraestructure.input.rest;

import com.microservicio.restaurant.application.dto.RestaurantRequest;
import com.microservicio.restaurant.application.dto.RestaurantResponse;
import com.microservicio.restaurant.application.handler.IRestaurantHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
