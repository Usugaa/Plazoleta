package com.restaurant.plazoleta.application.handler;

import com.restaurant.plazoleta.application.dto.UserRequest;
import com.restaurant.plazoleta.application.dto.UserResponse;

public interface IUserHandler {
    UserResponse saveAdmin(UserRequest userRequest);
    UserResponse saveOwner(UserRequest userRequest);
    UserResponse saveEmployee(UserRequest userRequest);
    UserResponse saveClient(UserRequest userRequest);
    Long getUserById(Long id);
    Long getRoleIdFromAuthenticatedUser();

}
