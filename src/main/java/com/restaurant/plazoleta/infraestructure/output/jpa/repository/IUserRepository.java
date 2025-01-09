package com.restaurant.plazoleta.infraestructure.output.jpa.repository;

import com.restaurant.plazoleta.infraestructure.output.jpa.entity.UserEntity;
import org.hibernate.annotations.NotFound;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByDocumentNumber(String documentNumber);

    UserEntity findByEmailAndPassword(String email, String password);

    @NotFound
    UserEntity findByEmail(String email);
}