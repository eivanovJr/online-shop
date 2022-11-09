package ru.quipy.shop.delivery.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.quipy.shop.delivery.DeliveryStatus;

import java.util.Date;
import java.util.UUID;

@Getter
public class DeliveryEntity {
    private UUID id;
    private String address;
    private Long price;
    @Setter
    private Date date;
    @Setter
    private DeliveryStatus status = DeliveryStatus.BOOKED;

    public DeliveryEntity(UUID id, String address, Long price, Date date) {
        this.id = id;
        this.address = address;
        this.price = price;
        this.date = date;
    }
}
