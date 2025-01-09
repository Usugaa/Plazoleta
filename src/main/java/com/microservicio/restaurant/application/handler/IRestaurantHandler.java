package com.microservicio.restaurant.application.handler;

import com.microservicio.restaurant.application.dto.RestaurantRequest;
import com.microservicio.restaurant.application.dto.RestaurantResponse;

import java.util.List;

public interface IRestaurantHandler {

    RestaurantResponse saveRestaurant(RestaurantRequest restaurantRequest);

    List<RestaurantResponse> getAllRestaurants();
}
