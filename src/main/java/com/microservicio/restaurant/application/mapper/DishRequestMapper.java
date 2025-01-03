package com.microservicio.restaurant.application.mapper;

import com.microservicio.restaurant.application.dto.DishRequest;
import com.microservicio.restaurant.domain.model.Dish;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface DishRequestMapper {

    Dish toDish(DishRequest dishRequest);

}
