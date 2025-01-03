package com.microservicio.restaurant.application.mapper;

import com.microservicio.restaurant.application.dto.RestaurantRequest;
import com.microservicio.restaurant.domain.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
unmappedTargetPolicy = ReportingPolicy.IGNORE,
unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface RestaurantRequestMapper {

    Restaurant toDomain(RestaurantRequest restaurantRequest);

}
