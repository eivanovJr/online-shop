package ru.quipy.shop.order.events;

import lombok.Getter;
import ru.quipy.core.annotations.DomainEvent;
import ru.quipy.domain.Event;
import ru.quipy.shop.order.OrderAggregate;

import java.util.UUID;

@DomainEvent(name = "ORDER_CREATED_EVENT")
public class OrderCreatedEvent extends Event<OrderAggregate> {

    @Getter
    private final UUID orderId;

    @Getter
    private final UUID userId;

    public OrderCreatedEvent(UUID orderId, UUID userId) {
        super();
        this.orderId = orderId;
        this.userId = userId;
    }
}
