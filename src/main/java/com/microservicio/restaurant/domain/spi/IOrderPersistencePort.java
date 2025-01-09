package com.microservicio.restaurant.domain.spi;

import com.microservicio.restaurant.domain.model.Order;
import com.microservicio.restaurant.domain.model.OrderDishes;

import java.util.List;

public interface IOrderPersistencePort {

    Order saveOrder(Order order);


    void saveOrderDishes(List<OrderDishes> orderDishes);

    boolean existsActiveOrderByClientId(Long clientId);

}
