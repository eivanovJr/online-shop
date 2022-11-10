package ru.quipy.shop.product.events;

import lombok.Getter;
import ru.quipy.core.annotations.DomainEvent;
import ru.quipy.domain.Event;
import ru.quipy.shop.product.ProductAggregate;

import java.util.UUID;

@DomainEvent(name = "PRODUCT_CREATE_EVENT")
public class ProductCreateEvent extends Event<ProductAggregate> {
    @Getter
    private UUID productId;
    @Getter
    private String name;
    @Getter
    private Long price;

    public ProductCreateEvent(UUID id, String name, Long price) {
        super();
        this.productId = id;
        this.name = name;
        this.price = price;
    }
}
