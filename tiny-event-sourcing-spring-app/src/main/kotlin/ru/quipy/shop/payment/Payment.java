package ru.quipy.shop.payment;

import ru.quipy.core.annotations.StateTransitionFunc;
import ru.quipy.domain.AggregateState;
import ru.quipy.shop.order.OrderAggregate;
import ru.quipy.shop.order.events.OrderCreatedEvent;
import ru.quipy.shop.payment.entities.PaymentStatus;
import ru.quipy.shop.payment.events.PaymentChangeStatusEvent;
import ru.quipy.shop.payment.events.PaymentCreateEvent;
import ru.quipy.shop.payment.events.PaymentSetDateEvent;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

public class Payment implements AggregateState<UUID, PaymentAggregate> {
    private UUID id;
    private Optional<Date> paymentDate = Optional.empty();
    private Long totalPrice;
    private PaymentStatus status = PaymentStatus.AWAITING;

    public UUID getId() {
        return id;
    }

    @StateTransitionFunc
    public void createPayment(PaymentCreateEvent event) {
        id = event.getPaymentId();
        totalPrice = event.getTotalPrice();
    }

    @StateTransitionFunc
    public void changeStatus(PaymentChangeStatusEvent event) {
        status = event.getStatus();
    }

    @StateTransitionFunc
    public void setPaymentDate(PaymentSetDateEvent event) {
        paymentDate = Optional.of(event.getDate());
    }

    public PaymentCreateEvent createPayment(Long totalPrice) {
        return new PaymentCreateEvent(id, totalPrice);
    }

    public PaymentChangeStatusEvent changeStatus(PaymentStatus status) {
        //
        return new PaymentChangeStatusEvent(id, status);
    }

    public PaymentSetDateEvent setPaymentDate(Date date) {
        if (paymentDate.isPresent() || status == PaymentStatus.SUCCESSFULL) {
            throw new IllegalStateException("Payment was already completed");
        }
        return new PaymentSetDateEvent(id, date);
    }

}
