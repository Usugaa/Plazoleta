package com.microservicio.restaurant.domain.api;

import com.microservicio.restaurant.domain.model.Restaurant;

public interface IRestaurantServicePort {

    Restaurant saveRestaurant(Restaurant restaurant);

}
