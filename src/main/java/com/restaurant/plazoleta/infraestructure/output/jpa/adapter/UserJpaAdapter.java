package com.restaurant.plazoleta.infraestructure.output.jpa.adapter;

import com.restaurant.plazoleta.domain.model.User;
import com.restaurant.plazoleta.domain.spi.IUserPersistencePort;
import com.restaurant.plazoleta.infraestructure.exception.UserAlreadyExistsException;
import com.restaurant.plazoleta.infraestructure.output.jpa.entity.UserEntity;
import com.restaurant.plazoleta.infraestructure.output.jpa.mapper.UserEntityMapper;
import com.restaurant.plazoleta.infraestructure.output.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserJpaAdapter implements IUserPersistencePort {

    private final IUserRepository userRepository;
    private final UserEntityMapper userEntityMapper;

    @Override
    public User saveUser(User user) {
        if (userRepository.existsByDocumentNumber(user.getDocumentNumber())){
            throw new UserAlreadyExistsException("Usuario existente");
        }
        UserEntity userEntity = userEntityMapper.toEntity(user);
        UserEntity savedEntity = userRepository.save(userEntity);
        return userEntityMapper.toDomain(savedEntity);
    }

    @Override
    public boolean existsByDocumentNumber(String documentNumber) {
        return userRepository.existsByDocumentNumber(documentNumber);
    }

    @Override
    public User getUserById(Long id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El usuario con ID " + id + " no existe."));
        return userEntityMapper.toDomain(userEntity);
    }
}