package ru.quipy.shop.payment.events;

import lombok.Getter;
import ru.quipy.core.annotations.DomainEvent;
import ru.quipy.domain.Event;
import ru.quipy.shop.payment.PaymentAggregate;

import java.util.UUID;

@DomainEvent(name = "PAYMENT_CREATE_EVENT")
public class PaymentCreateEvent extends Event<PaymentAggregate> {
    @Getter
    private UUID paymentId;
    @Getter
    private Long totalPrice;

    public PaymentCreateEvent(UUID id, Long totalPrice) {
        super();
        this.paymentId = id;
        this.totalPrice = totalPrice;
    }
}
