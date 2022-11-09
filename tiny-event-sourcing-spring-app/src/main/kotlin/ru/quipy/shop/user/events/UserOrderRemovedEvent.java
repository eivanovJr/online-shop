package ru.quipy.shop.user.events;

import lombok.Getter;
import ru.quipy.core.annotations.DomainEvent;
import ru.quipy.domain.Event;
import ru.quipy.shop.order.Order;
import ru.quipy.shop.user.UserAggregate;

import java.util.UUID;

@Getter
@DomainEvent(name = "USER_ORDER_REMOVED_EVENT")
public class UserOrderRemovedEvent extends Event<UserAggregate> {
    private final UUID userId;

    private final Order order;

    public UserOrderRemovedEvent(UUID userId, Order order) {
        super();
        this.userId = userId;
        this.order = order;
    }
}