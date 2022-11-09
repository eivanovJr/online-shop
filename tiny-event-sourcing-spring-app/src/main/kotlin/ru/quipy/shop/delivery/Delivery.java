package ru.quipy.shop.delivery;

import org.jetbrains.annotations.Nullable;
import ru.quipy.core.annotations.StateTransitionFunc;
import ru.quipy.domain.Aggregate;
import ru.quipy.domain.AggregateState;
import ru.quipy.shop.delivery.entity.DeliveryEntity;
import ru.quipy.shop.delivery.events.DeliveryChangeDateEvent;
import ru.quipy.shop.delivery.events.DeliveryChangeStatusEvent;
import ru.quipy.shop.delivery.events.DeliveryCreateEvent;

import java.util.Date;
import java.util.UUID;

public class Delivery implements AggregateState<UUID, DeliveryAggregate> {

    private DeliveryEntity deliveryEntity;
    private DeliveryStatus status = DeliveryStatus.BOOKED;

    public UUID getId() {
        return deliveryEntity.getId();
    }

    @StateTransitionFunc
    public void createDelivery(DeliveryCreateEvent event) {
        deliveryEntity = new DeliveryEntity(event.getId(), event.getAddress(), event.getPrice(), event.getDate());
    }

    @StateTransitionFunc
    public void changeStatus(DeliveryChangeStatusEvent event) {
        deliveryEntity.setStatus(event.getStatus());
    }

    @StateTransitionFunc
    public void changeDate(DeliveryChangeDateEvent event) {
        deliveryEntity.setDate(event.getDate());
    }

    public DeliveryCreateEvent createDelivery(UUID id, String address, Long price, Date date) {
        return new DeliveryCreateEvent(id, address, price, date);
    }

    public DeliveryChangeStatusEvent changeStatus(DeliveryStatus status) {
        //проверка на корректный статус
        return new DeliveryChangeStatusEvent(deliveryEntity.getId(), status);
    }

    public DeliveryChangeDateEvent changeDate(Date date) {
        if (deliveryEntity.getStatus() == DeliveryStatus.PAID || deliveryEntity.getStatus() == DeliveryStatus.DELIVERED)
            throw new IllegalStateException("Delivery alaready completed");
        return new DeliveryChangeDateEvent(deliveryEntity.getId(), date);
    }
}
