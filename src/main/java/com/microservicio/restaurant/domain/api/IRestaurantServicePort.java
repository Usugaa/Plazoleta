package com.microservicio.restaurant.domain.api;

import com.microservicio.restaurant.domain.model.Dish;
import com.microservicio.restaurant.domain.model.Restaurant;

import java.util.List;

public interface IRestaurantServicePort {

    Restaurant saveRestaurant(Restaurant restaurant);

    List<Restaurant> getAllRestaurants();
}
