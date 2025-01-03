package com.microservicio.restaurant.infraestructure.output.jpa.adapter;

import com.microservicio.restaurant.domain.model.Dish;
import com.microservicio.restaurant.domain.spi.IDishPersistencePort;
import com.microservicio.restaurant.infraestructure.output.jpa.entity.DishEntity;
import com.microservicio.restaurant.infraestructure.output.jpa.mapper.DishEntityMapper;
import com.microservicio.restaurant.infraestructure.output.jpa.repository.IDishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DishJpaAdapter implements IDishPersistencePort {

    private final IDishRepository dishRepository;
    private final DishEntityMapper dishEntityMapper;

    @Override
    public Dish saveDish(Dish dish) {
       DishEntity dishEntity = dishEntityMapper.toEntity(dish);
       DishEntity savedEntity = dishRepository.save(dishEntity);
       return dishEntityMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Dish> findById(Long id) {
        Optional<DishEntity> optionalDishEntity = dishRepository.findById(id);
        return optionalDishEntity.map(dishEntityMapper::toDomain);
    }

    @Override
    public Dish updateDish(Dish dish) {
        DishEntity dishEntity = dishEntityMapper.toEntity(dish);
        DishEntity savedEntity = dishRepository.save(dishEntity);
        return dishEntityMapper.toDomain(savedEntity);
    }
}
