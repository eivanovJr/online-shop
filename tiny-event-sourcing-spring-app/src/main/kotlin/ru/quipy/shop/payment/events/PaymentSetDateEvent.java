package ru.quipy.shop.payment.events;

import lombok.Getter;
import ru.quipy.domain.Event;
import ru.quipy.shop.payment.Payment;
import ru.quipy.shop.payment.PaymentAggregate;

import java.util.Date;
import java.util.UUID;

public class PaymentSetDateEvent extends Event<PaymentAggregate> {
    @Getter
    private Date date;
    @Getter
    private UUID paymentId;

    public PaymentSetDateEvent(UUID paymentId, Date date) {
        super();
        this.paymentId = paymentId;
        this.date = date;
    }
}
