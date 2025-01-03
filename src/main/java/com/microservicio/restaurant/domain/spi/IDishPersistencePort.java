package com.microservicio.restaurant.domain.spi;

import com.microservicio.restaurant.domain.model.Dish;

import java.util.Optional;

public interface IDishPersistencePort {

    Dish saveDish(Dish dish);

    Optional<Dish>

    findById(Long id);

    Dish updateDish(Dish dish);
}