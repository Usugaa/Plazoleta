package com.microservicio.restaurant.infraestructure.output.jpa.adapter;

import com.microservicio.restaurant.domain.model.Restaurant;
import com.microservicio.restaurant.domain.spi.IRestaurantePersistencePort;
import com.microservicio.restaurant.infraestructure.output.jpa.entity.RestaurantEntity;
import com.microservicio.restaurant.infraestructure.output.jpa.mapper.RestaurantEntityMapper;
import com.microservicio.restaurant.infraestructure.output.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RestaurantJpaAdapter implements IRestaurantePersistencePort {

    private final IRestaurantRepository restaurantRepository;
    private final RestaurantEntityMapper restaurantEntityMapper;

    @Override
    public Restaurant saveRestaurant(Restaurant restaurant) {
        RestaurantEntity restaurantEntity= restaurantEntityMapper.toEntity(restaurant);
        RestaurantEntity saveEntity = restaurantRepository.save(restaurantEntity);
        return restaurantEntityMapper.toDomain(saveEntity);
    }

    @Override
    public List<Restaurant> findAllRestaurants() {
        // Obtener todos los restaurantes desde la base de datos
        List<RestaurantEntity> restaurantEntities = restaurantRepository.findAll();
        // Mapeamos los restaurantes de entidad a dominio
        return restaurantEntities.stream()
                .map(restaurantEntityMapper::toDomain)
                .toList();
    }
}