package com.restaurant.plazoleta.application.mapper;

import com.restaurant.plazoleta.application.dto.UserResponse;
import com.restaurant.plazoleta.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
unmappedTargetPolicy = ReportingPolicy.IGNORE,
unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserResponseMapper {

    UserResponse toResponse(User user);

    List<UserResponse> toResponseList(List<User> users);

}