package com.microservicio.restaurant.infraestructure.output.jpa.mapper;

import com.microservicio.restaurant.domain.model.Restaurant;
import com.microservicio.restaurant.infraestructure.output.jpa.entity.RestaurantEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface RestaurantEntityMapper {

    RestaurantEntity toEntity(Restaurant restaurant);

    Restaurant toDomain(RestaurantEntity restaurantEntity);

}
