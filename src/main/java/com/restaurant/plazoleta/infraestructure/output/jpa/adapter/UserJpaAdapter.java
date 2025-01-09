package com.restaurant.plazoleta.infraestructure.output.jpa.adapter;

import com.restaurant.plazoleta.domain.model.User;
import com.restaurant.plazoleta.domain.spi.IUserPersistencePort;
import com.restaurant.plazoleta.infraestructure.exception.NoDataFoundException;
import com.restaurant.plazoleta.infraestructure.output.jpa.entity.UserEntity;
import com.restaurant.plazoleta.infraestructure.output.jpa.mapper.UserEntityMapper;
import com.restaurant.plazoleta.infraestructure.output.jpa.mapper.UserEntityMapper2;
import com.restaurant.plazoleta.infraestructure.output.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class UserJpaAdapter implements IUserPersistencePort {

    private final IUserRepository userRepository;
    private final UserEntityMapper userEntityMapper;

    @Override
    public User saveUser(User user) {
        UserEntity userEntity = userEntityMapper.toEntity(user);
        UserEntity savedEntity = userRepository.save(userEntity);
        return userEntityMapper.toDomain(savedEntity);
    }

    @Override
    public boolean existsByDocumentNumber(String documentNumber) {
        return userRepository.existsByDocumentNumber(documentNumber);
    }

    @Override
    public User getByEmailAndPassword(String email, String password) {
        UserEntity userEntity = userRepository.findByEmailAndPassword(email, password);
        return UserEntityMapper2.toDomain(userEntity);
    }

    @Override
    public User getByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (Objects.isNull(userEntity)) {
            throw new NoDataFoundException("User not found");
        }
        return UserEntityMapper2.toDomain(userEntity);
    }

    @Override
    public User getUserById(Long id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El usuario con ID " + id + " no existe."));
        return userEntityMapper.toDomain(userEntity);
    }
}