package com.microservicio.restaurant.domain.usecase;

import com.microservicio.restaurant.domain.api.IRestaurantServicePort;
import com.microservicio.restaurant.domain.model.Restaurant;
import com.microservicio.restaurant.domain.spi.IRestaurantePersistencePort;
import com.microservicio.restaurant.domain.spi.IUserPersistencePort;

import java.util.List;

public class RestaurantUseCase implements IRestaurantServicePort {

    private final IRestaurantePersistencePort restaurantePersistencePort;
    private final IUserPersistencePort userPersistencePort;

    public RestaurantUseCase(IRestaurantePersistencePort restaurantePersistencePort, IUserPersistencePort userPersistencePort) {
        this.restaurantePersistencePort = restaurantePersistencePort;
        this.userPersistencePort = userPersistencePort;
    }


    @Override
    public Restaurant saveRestaurant(Restaurant restaurant) {

        userPersistencePort.getUserById(restaurant.getIdOwner());

        restaurantePersistencePort.saveRestaurant(restaurant);
        return restaurant;
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurantePersistencePort.findAllRestaurants();
    }
}