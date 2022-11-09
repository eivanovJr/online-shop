package ru.quipy.shop.order.events;


import lombok.Getter;
import ru.quipy.core.annotations.DomainEvent;
import ru.quipy.domain.Event;
import ru.quipy.shop.order.OrderAggregate;

import java.util.UUID;

@DomainEvent(name = "ORDER_PAYMENT_LINKED")
public class OrderPaymentSetEvent extends Event<OrderAggregate> {

    @Getter
    private final UUID orderId;

    @Getter
    private final UUID paymentId;

    public OrderPaymentSetEvent(UUID orderId, UUID paymentId) {
        super();
        this.orderId = orderId;
        this.paymentId = paymentId;
    }
}
