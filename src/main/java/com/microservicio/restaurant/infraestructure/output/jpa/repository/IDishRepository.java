package com.microservicio.restaurant.infraestructure.output.jpa.repository;

import com.microservicio.restaurant.infraestructure.output.jpa.entity.DishEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDishRepository extends JpaRepository<DishEntity, Long> {
}
