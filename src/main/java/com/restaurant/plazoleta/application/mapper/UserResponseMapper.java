package com.restaurant.plazoleta.application.mapper;

import com.restaurant.plazoleta.application.dto.UserResponse;
import com.restaurant.plazoleta.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
unmappedTargetPolicy = ReportingPolicy.IGNORE,
unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserResponseMapper {

    UserResponse toResponse(User user);

}