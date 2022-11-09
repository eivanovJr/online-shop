package ru.quipy.shop.order.events;

import ru.quipy.core.annotations.DomainEvent;
import ru.quipy.domain.Event;
import ru.quipy.shop.order.OrderAggregate;
import ru.quipy.shop.order.entities.OrderStatus;

import java.util.UUID;

@DomainEvent(name = "ORDER_CHANGE_STATUS_EVENT")
public class OrderChangeStatusEvent extends Event<OrderAggregate> {
    private OrderStatus status;
    private UUID orderId;

    public OrderChangeStatusEvent(UUID orderId, OrderStatus status) {
        super();
        this.orderId = orderId;
        this.status = status;
    }
}
