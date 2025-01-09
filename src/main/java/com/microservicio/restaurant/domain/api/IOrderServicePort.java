package com.microservicio.restaurant.domain.api;

import com.microservicio.restaurant.domain.model.Order;
import com.microservicio.restaurant.domain.model.OrderDishes;

import java.util.List;

public interface IOrderServicePort {

    void createOrder(Order order, List<OrderDishes> orderDishes);
    boolean hasActiveOrder(Long idClient);


}
