package com.microservicio.restaurant.infraestructure.output.jpa.adapter;

import com.microservicio.restaurant.domain.spi.IUserPersistencePort;
import com.microservicio.restaurant.infraestructure.input.client.UserFeignClient;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserAdapter implements IUserPersistencePort {

    private final UserFeignClient userFeignClient;

    @Override
    public Long getUserById(Long id) {
        return userFeignClient.getUserById(id).getIdRole();
    }
}