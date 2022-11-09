package ru.quipy.shop.delivery.events;

import lombok.Getter;
import ru.quipy.core.annotations.DomainEvent;
import ru.quipy.domain.Event;
import ru.quipy.shop.delivery.DeliveryAggregate;

import java.util.Date;
import java.util.UUID;

@Getter
@DomainEvent(name = "DELIVERY_CHANGE_DATE_EVENT")
public class DeliveryChangeDateEvent extends Event<DeliveryAggregate> {
    private UUID deliveryId;
    private Date date;

    public DeliveryChangeDateEvent(UUID id, Date date) {
        super();
        this.date = date;
        this.deliveryId = id;
    }
}
