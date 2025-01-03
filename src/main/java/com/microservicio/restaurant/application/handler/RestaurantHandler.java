package com.microservicio.restaurant.application.handler;

import com.microservicio.restaurant.application.dto.RestaurantRequest;
import com.microservicio.restaurant.application.dto.RestaurantResponse;
import com.microservicio.restaurant.application.mapper.RestaurantRequestMapper;
import com.microservicio.restaurant.application.mapper.RestaurantResponseMapper;
import com.microservicio.restaurant.domain.api.IRestaurantServicePort;
import com.microservicio.restaurant.domain.model.Restaurant;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class RestaurantHandler implements IRestaurantHandler{

    private final IRestaurantServicePort restaurantServicePort;
    private final RestaurantRequestMapper restaurantRequestMapper;
    private final RestaurantResponseMapper restaurantResponseMapper;

    @Override
    public RestaurantResponse saveRestaurant(RestaurantRequest restaurantRequest) {
        Restaurant restaurant = restaurantRequestMapper.toDomain(restaurantRequest);
        Restaurant saveRestaurant = restaurantServicePort.saveRestaurant(restaurant);
        return restaurantResponseMapper.toResponse(saveRestaurant);
    }
}
