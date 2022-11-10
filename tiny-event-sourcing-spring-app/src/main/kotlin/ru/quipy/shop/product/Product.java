package ru.quipy.shop.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.quipy.core.annotations.StateTransitionFunc;
import ru.quipy.shop.payment.entities.PaymentStatus;
import ru.quipy.shop.payment.events.PaymentChangeStatusEvent;
import ru.quipy.shop.product.events.ProductChangePrice;
import ru.quipy.shop.product.events.ProductCreateEvent;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class Product {
    private UUID id;
    private final String name;
    @Setter
    private Long price;

    public UUID getId() {
        return id;
    }

    @StateTransitionFunc
    public void createProduct(ProductCreateEvent event) {
        id = event.getProductId();
    }

    @StateTransitionFunc
    public void changePrice(ProductChangePrice event) {
        price = event.getPrice();
    }

    public ProductChangePrice changeStatus(Long price) {
        return new ProductChangePrice(id, price);
    }

    public ProductCreateEvent createProduct(Long totalPrice) {
        return new ProductCreateEvent(id, name, price);
    }
}
