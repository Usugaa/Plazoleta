package com.microservicio.restaurant.domain.usecase;

import com.microservicio.restaurant.domain.api.IOrderServicePort;
import com.microservicio.restaurant.domain.model.Order;
import com.microservicio.restaurant.domain.model.OrderDishes;
import com.microservicio.restaurant.domain.spi.IOrderPersistencePort;

import java.util.List;

public class OrderUseCase implements IOrderServicePort {

    private final IOrderPersistencePort orderPersistencePort;

    public OrderUseCase(IOrderPersistencePort orderPersistencePort) {
        this.orderPersistencePort = orderPersistencePort;
    }

    @Override
    public void createOrder(Order order, List<OrderDishes> orderDishes) {
        // Verificar si el cliente tiene un pedido activo
        if (hasActiveOrder(order.getIdClient())) {
            throw new BusinessException("El cliente ya tiene un pedido en proceso.");
        }

        // Establecer estado inicial del pedido
        order.setStatus("Pendiente");

        // Guardar el pedido
        orderPersistencePort.saveOrder(order);

        // Asociar los platos al pedido
        for (OrderDishes orderDish : orderDishes) {
            orderDish.setOrderId(order.getId());
            orderPersistencePort.saveOrderDish(orderDish);
        }
    }

    @Override
    public boolean hasActiveOrder(Long idClient) {
        return orderPersistencePort.hasActiveOrder(idClient);

    }
}