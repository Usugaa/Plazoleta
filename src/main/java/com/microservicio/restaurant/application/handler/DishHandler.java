package com.microservicio.restaurant.application.handler;

import com.microservicio.restaurant.application.dto.DishRequest;
import com.microservicio.restaurant.application.dto.DishResponse;
import com.microservicio.restaurant.application.mapper.DishRequestMapper;
import com.microservicio.restaurant.application.mapper.DishResponseMapper;
import com.microservicio.restaurant.domain.api.IDishServicePort;
import com.microservicio.restaurant.domain.model.Dish;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class DishHandler implements IDishHandler{

    private final IDishServicePort dishServicePort;
    private final DishRequestMapper dishRequestMapper;
    private final DishResponseMapper dishResponseMapper;

    @Override
    public DishResponse saveDish(DishRequest dishRequest) {
        Dish dish = dishRequestMapper.toDish(dishRequest);
        Dish savedDish = dishServicePort.saveDish(dish);
        return dishResponseMapper.toResponse(savedDish);
    }

    @Override
    public DishResponse updateDishDescriptionAndPrice(Long id, String description, int price) {
        Dish updateDish = dishServicePort.updateDish(id, description, price);
        return dishResponseMapper.toResponse(updateDish);
    }
}
