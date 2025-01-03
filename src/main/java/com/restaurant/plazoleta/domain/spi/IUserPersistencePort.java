package com.restaurant.plazoleta.domain.spi;

import com.restaurant.plazoleta.domain.model.User;

public interface IUserPersistencePort {

    User saveUser (User user);

    boolean existsByDocumentNumber(String documentNumber);

    User getUserById(Long id);
}