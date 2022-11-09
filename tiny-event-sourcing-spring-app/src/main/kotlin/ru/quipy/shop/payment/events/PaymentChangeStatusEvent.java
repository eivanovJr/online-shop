package ru.quipy.shop.payment.events;

import lombok.Getter;
import ru.quipy.core.annotations.DomainEvent;
import ru.quipy.domain.Event;
import ru.quipy.shop.payment.PaymentAggregate;
import ru.quipy.shop.payment.entities.PaymentStatus;

import java.util.UUID;

@DomainEvent(name = "PAYMENT_CHANGE_STATUS_EVENT")
public class PaymentChangeStatusEvent extends Event<PaymentAggregate> {

    @Getter
    private UUID paymentId;

    @Getter
    private PaymentStatus status;

    public PaymentChangeStatusEvent(UUID paymentId, PaymentStatus status) {
        super();
        this.paymentId = paymentId;
        this.status = status;
    }
}
