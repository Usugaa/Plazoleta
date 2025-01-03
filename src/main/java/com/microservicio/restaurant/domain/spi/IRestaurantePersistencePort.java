package com.microservicio.restaurant.domain.spi;

import com.microservicio.restaurant.domain.model.Restaurant;

public interface IRestaurantePersistencePort {

    Restaurant saveRestaurant(Restaurant restaurant);

}
