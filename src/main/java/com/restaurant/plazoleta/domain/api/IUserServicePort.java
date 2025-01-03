package com.restaurant.plazoleta.domain.api;

import com.restaurant.plazoleta.domain.model.User;

public interface IUserServicePort {

    User saveUser (User user);

    User getUserById(Long id);

}