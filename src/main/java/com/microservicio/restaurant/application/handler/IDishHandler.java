package com.microservicio.restaurant.application.handler;

import com.microservicio.restaurant.application.dto.DishRequest;
import com.microservicio.restaurant.application.dto.DishResponse;

public interface IDishHandler {

    DishResponse saveDish(DishRequest dishRequest);

    DishResponse updateDishDescriptionAndPrice(Long id, String description, int price);

}
