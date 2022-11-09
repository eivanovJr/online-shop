package ru.quipy.shop.user.events;

import lombok.Getter;
import ru.quipy.core.annotations.DomainEvent;
import ru.quipy.domain.Event;
import ru.quipy.shop.user.UserAggregate;

import java.util.UUID;

@Getter
@DomainEvent(name = "USER_CHANGE_PHONE_EVENT")
public class UserChangePhoneEvent extends Event<UserAggregate> {
    private final UUID userId;
    private final String phone;

    public UserChangePhoneEvent(UUID userId, String phone) {
        super();
        this.userId = userId;
        this.phone = phone;
    }
}