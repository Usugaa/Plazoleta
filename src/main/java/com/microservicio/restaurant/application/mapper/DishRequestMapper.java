package com.microservicio.restaurant.application.mapper;

import com.microservicio.restaurant.application.dto.DishRequest;
import com.microservicio.restaurant.domain.model.Dish;

public class DishRequestMapper {

    public Dish toDish(DishRequest dishRequest) {
        Dish dish = new Dish();
        dish.setName(dishRequest.getName());
        dish.setDescription(dishRequest.getDescription());
        dish.setPrice(dishRequest.getPrice());
        dish.setUrlImage(dishRequest.getUrlImage());
        // Si tienes idCategory y idRestaurant, asegúrate de asignarlos aquí si los estás pasando desde el request
        if (dishRequest.getIdCategory() != null) {
            dish.setIdCategory(dishRequest.getIdCategory());
        }
        if (dishRequest.getIdRestaurant() != null) {
            dish.setIdRestaurant(dishRequest.getIdRestaurant());
        }
        return dish;
    }

}
