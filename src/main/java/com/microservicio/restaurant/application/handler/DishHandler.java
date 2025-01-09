package com.microservicio.restaurant.application.handler;

import com.microservicio.restaurant.application.dto.DishRequest;
import com.microservicio.restaurant.application.dto.DishResponse;
import com.microservicio.restaurant.application.dto.UpdateDishRequest;
import com.microservicio.restaurant.application.dto.UpdateDishStatusRequest;
import com.microservicio.restaurant.application.mapper.DishRequestMapper;
import com.microservicio.restaurant.application.mapper.DishResponseMapper;
import com.microservicio.restaurant.domain.api.IDishServicePort;
import com.microservicio.restaurant.domain.model.Dish;
import com.microservicio.restaurant.domain.spi.IDishPersistencePort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class DishHandler implements IDishHandler{

    private final IDishServicePort dishServicePort;
    private final IDishPersistencePort dishPersistencePort;
    private final DishRequestMapper dishRequestMapper;
    private final DishResponseMapper dishResponseMapper;

    @Override
    public DishResponse saveDish(DishRequest dishRequest) {
        Dish dish = dishRequestMapper.toDish(dishRequest);
        Dish savedDish = dishServicePort.saveDish(dish);
        return dishResponseMapper.toResponse(savedDish);
    }

    @Override
    public DishResponse updateDishDescriptionAndPrice(Long id, UpdateDishRequest updateDishRequest) {
        Dish updateDish = dishServicePort.updateDish(
                id,
                updateDishRequest.getDescription(),
                updateDishRequest.getPrice()
        );
        return dishResponseMapper.toResponse(updateDish);
    }

    @Override
    public Optional<DishResponse> findDishById(Long id) {
        Optional<Dish> dish = dishServicePort.findDishById(id);
        return dish.map(dishResponseMapper::toResponse);
    }

    @Override
    public DishResponse updateDishStatus(Long id, UpdateDishStatusRequest updateDishStatusRequest) {
        Dish updateDish = dishServicePort.updateDishStatus(id, updateDishStatusRequest.isActive());
        return dishResponseMapper.toResponse(updateDish);
    }

    @Override
    public List<DishResponse> getDishesByRestaurant(Long idRestaurant) {
        // Aquí usamos el Persistence Port
        return dishPersistencePort.findDishesByRestaurant(idRestaurant)
                .stream()
                .map(dishResponseMapper::toResponse)
                .toList();
    }
}