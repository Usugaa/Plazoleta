package com.restaurant.plazoleta.application.handler;

import com.restaurant.plazoleta.application.dto.UserRequest;
import com.restaurant.plazoleta.application.dto.UserResponse;

public interface IUserHandler {
    UserResponse saveUser(UserRequest userRequest);

    Long getUserById (Long id);

}
