package ru.quipy.shop.delivery.events;

import lombok.Getter;
import ru.quipy.core.annotations.DomainEvent;
import ru.quipy.domain.Event;
import ru.quipy.shop.delivery.DeliveryAggregate;
import ru.quipy.shop.delivery.DeliveryStatus;
import sun.awt.util.IdentityLinkedList;

import java.util.UUID;

@Getter
@DomainEvent(name = "DELIVERY_CHANGE_STATUS_EVENT")
public class DeliveryChangeStatusEvent extends Event<DeliveryAggregate> {
    private UUID deliveryId;
    private DeliveryStatus status;

    public DeliveryChangeStatusEvent(UUID id, DeliveryStatus status) {
        super();
        this.deliveryId = id;
        this.status = status;
    }
}
