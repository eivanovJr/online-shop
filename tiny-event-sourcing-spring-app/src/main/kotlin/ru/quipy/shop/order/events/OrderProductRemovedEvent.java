package ru.quipy.shop.order.events;

import lombok.Getter;
import ru.quipy.core.annotations.DomainEvent;
import ru.quipy.domain.Event;
import ru.quipy.shop.order.entities.Product;
import ru.quipy.shop.order.OrderAggregate;

import java.util.UUID;


@DomainEvent(name = "PRODUCT_REMOVED_EVENT")
public class OrderProductRemovedEvent extends Event<OrderAggregate> {

    @Getter
    private final UUID orderId;

    @Getter
    private final Product product;

    public OrderProductRemovedEvent(UUID orderId, Product product) {
        super();
        this.orderId = orderId;
        this.product = product;
    }
}
