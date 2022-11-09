package ru.quipy.shop.order.events;


import lombok.Getter;
import ru.quipy.core.annotations.DomainEvent;
import ru.quipy.domain.Event;
import ru.quipy.shop.order.entities.Product;
import ru.quipy.shop.order.OrderAggregate;

import java.util.UUID;


@DomainEvent(name = "PRODUCT_ADDED_EVENT")
public class OrderProductAddedEvent extends Event<OrderAggregate> {

    @Getter
    private final UUID orderId;

    @Getter
    private final Product product;

    public OrderProductAddedEvent(UUID orderId, Product product) {
        super();
        this.setCreatedAt(System.currentTimeMillis());
        this.orderId = orderId;
        this.product = product;
    }
}
