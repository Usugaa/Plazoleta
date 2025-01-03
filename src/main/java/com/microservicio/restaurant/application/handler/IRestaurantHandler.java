package com.microservicio.restaurant.application.handler;

import com.microservicio.restaurant.application.dto.RestaurantRequest;
import com.microservicio.restaurant.application.dto.RestaurantResponse;

public interface IRestaurantHandler {

    RestaurantResponse saveRestaurant(RestaurantRequest restaurantRequest);

}
