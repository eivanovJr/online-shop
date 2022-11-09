package ru.quipy.shop.delivery.events;


import lombok.Getter;
import ru.quipy.core.annotations.DomainEvent;
import ru.quipy.domain.Event;
import ru.quipy.shop.delivery.DeliveryAggregate;
import ru.quipy.shop.delivery.entity.DeliveryEntity;

import java.util.Date;
import java.util.UUID;

@Getter
@DomainEvent(name = "DELIVERY_CREATE_EVENT")
public class DeliveryCreateEvent extends Event<DeliveryAggregate> {
    private UUID id;
    private String address;
    private Long price;
    private Date date;

    public DeliveryCreateEvent(UUID id, String address, Long price, Date date) {
        super();
        this.id = id;
        this.address = address;
        this.price = price;
        this.date = date;
    }
}
