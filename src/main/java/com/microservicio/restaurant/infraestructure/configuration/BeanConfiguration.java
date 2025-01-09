package com.microservicio.restaurant.infraestructure.configuration;

import com.microservicio.restaurant.application.mapper.DishRequestMapper;
import com.microservicio.restaurant.domain.api.IDishServicePort;
import com.microservicio.restaurant.domain.api.IRestaurantServicePort;
import com.microservicio.restaurant.domain.spi.IDishPersistencePort;
import com.microservicio.restaurant.domain.spi.IRestaurantePersistencePort;
import com.microservicio.restaurant.domain.spi.IUserPersistencePort;
import com.microservicio.restaurant.domain.usecase.DishUseCase;
import com.microservicio.restaurant.domain.usecase.RestaurantUseCase;
import com.microservicio.restaurant.infraestructure.input.client.UserFeignClient;
import com.microservicio.restaurant.infraestructure.output.jpa.adapter.DishJpaAdapter;
import com.microservicio.restaurant.infraestructure.output.jpa.adapter.RestaurantJpaAdapter;
import com.microservicio.restaurant.infraestructure.output.jpa.adapter.UserAdapter;
import com.microservicio.restaurant.infraestructure.output.jpa.mapper.DishEntityMapper;
import com.microservicio.restaurant.infraestructure.output.jpa.mapper.RestaurantEntityMapper;
import com.microservicio.restaurant.infraestructure.output.jpa.repository.IDishRepository;
import com.microservicio.restaurant.infraestructure.output.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IRestaurantRepository restaurantRepository;
    private final RestaurantEntityMapper restaurantEntityMapper;
    private final IDishRepository dishRepository;
    private final DishEntityMapper dishEntityMapper;
    private final UserFeignClient userFeignClient;

    @Bean
    public IRestaurantePersistencePort restaurantePersistencePort(){
        return new RestaurantJpaAdapter(restaurantRepository, restaurantEntityMapper);
    }

    @Bean
    public IRestaurantServicePort restaurantServicePort(IRestaurantePersistencePort restaurantePersistencePort){
        return new RestaurantUseCase(restaurantePersistencePort, userPersistencePort());
    }

    @Bean
    public IUserPersistencePort userPersistencePort(){return new UserAdapter(userFeignClient);
    }

    @Bean
    public IDishPersistencePort dishPersistencePort(IDishRepository dishRepository, DishEntityMapper dishEntityMapper) {
        return new DishJpaAdapter(dishRepository, dishEntityMapper);
    }

    @Bean
    public IDishServicePort dishServicePort(IDishPersistencePort dishPersistencePort){
        return new DishUseCase(dishPersistencePort);
    }

    @Bean
    public DishRequestMapper dishRequestMapper() {
        return new DishRequestMapper();
    }
}