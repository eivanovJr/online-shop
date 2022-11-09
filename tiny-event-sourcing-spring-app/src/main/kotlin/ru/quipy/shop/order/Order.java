package ru.quipy.shop.order;

import ru.quipy.core.annotations.StateTransitionFunc;
import ru.quipy.domain.AggregateState;
import ru.quipy.shop.order.entities.OrderStatus;
import ru.quipy.shop.order.entities.Product;
import ru.quipy.shop.order.events.*;

import java.util.*;


public class Order implements AggregateState<UUID, OrderAggregate> {
    private UUID id;
    private UUID userId;
    private final Map<UUID, Integer> products = new HashMap<>();

    private long price = 0L;

    private long assemblingTime; // это хз, может снести

    private OrderStatus status = OrderStatus.COLLECTING;

    private Optional<UUID> deliveryId = Optional.empty();

    private Optional<UUID> paymentId = Optional.empty();

    public UUID getId() {
        return id;
    }

    public Order(UUID id, UUID userId) {
        this.id = id;
        this.userId = userId;
    }

    @StateTransitionFunc
    public void addProduct(OrderProductAddedEvent event) {
        UUID productId = event.getProduct().getId();
        products.put(productId, products.getOrDefault(productId, 0) + 1);
        price += event.getProduct().getPrice();
    }

    @StateTransitionFunc
    public void removeProduct(OrderProductRemovedEvent event) {
        UUID productId = event.getProduct().getId();
        Integer quantity = products.get(productId);
        if (quantity == 1) {
            products.remove(productId);
        } else {
            products.put(productId, products.get(productId) - 1);
        }
        price -= event.getProduct().getPrice();
    }

    @StateTransitionFunc
    public void orderCreated(OrderCreatedEvent event) {
        id = event.getOrderId();
        userId = event.getUserId();
    }

    @StateTransitionFunc
    public void deliverySet(OrderDeliverySetEvent event) {
        deliveryId = Optional.of(event.getDeliveryId());
    }

    @StateTransitionFunc
    public void paymentSet(OrderPaymentSetEvent event) {
        paymentId = Optional.of(event.getPaymentId());
    }

    public OrderCreatedEvent createOrder(UUID id, UUID userId) {
        return new OrderCreatedEvent(id, userId);
    }

    public OrderDeliverySetEvent setDelivery(UUID deliveryId) {
        if (this.deliveryId.isPresent()) {
            throw new IllegalStateException("Delivery already set in order " + id);
        }
        return new OrderDeliverySetEvent(this.id, deliveryId);
    }

    public OrderPaymentSetEvent setPayment(UUID paymentId) {
        if (this.paymentId.isPresent()) {
            throw new IllegalStateException("Payment already set in order " + id);
        }
        return new OrderPaymentSetEvent(this.id, paymentId);
    }

    public OrderProductAddedEvent addProduct(Product product) {
        return new OrderProductAddedEvent(this.id, product);
    }

    public OrderProductRemovedEvent deleteProduct(Product product) {
        if (!products.containsKey(product.getId())) {
            throw new IllegalStateException("no such product in order");
        }
        return new OrderProductRemovedEvent(this.id, product);
    }

    public OrderChangeStatusEvent changeStatus(OrderStatus status) {
        //проверка, что из текущего статуса можно получить желаемый ???
        return new OrderChangeStatusEvent(this.id, status);
    }

}
