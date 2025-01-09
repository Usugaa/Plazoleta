package com.microservicio.restaurant.infraestructure.output.jpa.adapter;

import com.microservicio.restaurant.domain.api.IDishServicePort;
import com.microservicio.restaurant.domain.model.Dish;
import com.microservicio.restaurant.domain.spi.IDishPersistencePort;
import com.microservicio.restaurant.infraestructure.output.jpa.entity.DishEntity;
import com.microservicio.restaurant.infraestructure.output.jpa.mapper.DishEntityMapper;
import com.microservicio.restaurant.infraestructure.output.jpa.repository.IDishRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
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
    public Optional<Dish> findDishById(Long id) {
        Optional<DishEntity> dishEntityOptional = dishRepository.findById(id);
        return dishEntityOptional.map(dishEntityMapper::toDomain);
    }

    @Override
    public Dish updateDish(Long id, String description, int price) {
        DishEntity dishEntity = dishRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El plato con id " + id + " no existe."));
        dishEntity.setDescription(description);
        dishEntity.setPrice(price);

        DishEntity updateDishEntity = dishRepository.save(dishEntity);

        return dishEntityMapper.toDomain(updateDishEntity);
    }

    @Override
    public Dish updateDishStatus(Long id, boolean active) {
        DishEntity dishEntity = dishRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dish not found with id: " + id));
        dishEntity.setActive(active);
        return dishEntityMapper.toDomain(dishRepository.save(dishEntity));
    }

    @Override
    public List<Dish> findDishesByRestaurant(Long idRestaurant) {
        List<DishEntity> dishEntities = dishRepository.findByRestaurantId(idRestaurant);
        return dishEntities.stream()
                .map(dishEntityMapper::toDomain)
                .toList();
    }
}
