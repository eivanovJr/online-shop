package ru.quipy.shop.user.events;

import lombok.Getter;
import ru.quipy.core.annotations.DomainEvent;
import ru.quipy.domain.Event;
import ru.quipy.shop.user.UserAggregate;

import java.util.UUID;

@Getter
@DomainEvent(name = "USER_CHANGE_ADDRESS_EVENT")
public class UserChangeAddressEvent extends Event<UserAggregate> {
    private final UUID userId;
    private final String address;

    public UserChangeAddressEvent(UUID userId, String address) {
        super();
        this.userId = userId;
        this.address = address;
    }
}