package com.microservicio.restaurant.application.mapper;

import com.microservicio.restaurant.application.dto.DishResponse;
import com.microservicio.restaurant.domain.model.Dish;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface DishResponseMapper {

    DishResponse toResponse(Dish dish);

}
