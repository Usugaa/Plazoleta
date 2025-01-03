package com.microservicio.restaurant.domain.api;

import com.microservicio.restaurant.domain.model.Dish;

import java.util.Optional;

public interface IDishServicePort {

    Dish saveDish(Dish dish);

    Dish updateDish(Long id, String description, int price);

}