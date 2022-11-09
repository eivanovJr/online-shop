package ru.quipy.shop.order.events;


import lombok.Getter;
import ru.quipy.core.annotations.DomainEvent;
import ru.quipy.domain.Event;
import ru.quipy.shop.order.OrderAggregate;

import java.util.UUID;

@DomainEvent(name = "ORDER_DELIVERY_SET")
public class OrderDeliverySetEvent extends Event<OrderAggregate> {

    @Getter
    private UUID orderId;

    @Getter
    private UUID deliveryId;

    public OrderDeliverySetEvent(UUID orderId, UUID deliveryId) {
        super();
        this.deliveryId = deliveryId;
        this.orderId = orderId;
    }
}
