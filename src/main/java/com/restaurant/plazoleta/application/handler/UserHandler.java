package com.restaurant.plazoleta.application.handler;

import com.restaurant.plazoleta.application.dto.UserRequest;
import com.restaurant.plazoleta.application.dto.UserResponse;
import com.restaurant.plazoleta.application.mapper.UserRequestMapper;
import com.restaurant.plazoleta.application.mapper.UserResponseMapper;
import com.restaurant.plazoleta.domain.api.IUserServicePort;
import com.restaurant.plazoleta.domain.model.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class UserHandler implements IUserHandler {

    private final IUserServicePort userServicePort;
    private final UserRequestMapper userRequestMapper;
    private final UserResponseMapper userResponseMapper;

    @Override
    public UserResponse saveUser(UserRequest userRequest) {
        User user = userRequestMapper.toDomain(userRequest);
        User saveUser = userServicePort.saveUser(user);
        return userResponseMapper.toResponse(saveUser);
    }

    @Override
    public Long getUserById(Long id) {

        User user = userServicePort.getUserById(id);
        if (user == null) {
            throw new IllegalArgumentException("Usuario no encontrado para el ID: " + id);
        }

        return user.getId();

    }
}