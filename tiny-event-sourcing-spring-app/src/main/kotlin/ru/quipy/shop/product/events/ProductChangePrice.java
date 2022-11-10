package ru.quipy.shop.product.events;

import lombok.Getter;
import ru.quipy.core.annotations.DomainEvent;
import ru.quipy.domain.Event;
import ru.quipy.shop.product.ProductAggregate;

import java.util.UUID;

@DomainEvent(name = "PAYMENT_CHANGE_STATUS_EVENT")
public class ProductChangePrice extends Event<ProductAggregate> {

    @Getter
    private UUID productId;

    @Getter
    private Long price;

    public ProductChangePrice(UUID productId, Long price) {
        super();
        this.productId = productId;
        this.price = price;
    }
}
