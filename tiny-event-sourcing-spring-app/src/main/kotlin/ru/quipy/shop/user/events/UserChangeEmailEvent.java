package ru.quipy.shop.user.events;

import lombok.Getter;
import ru.quipy.core.annotations.DomainEvent;
import ru.quipy.domain.Event;
import ru.quipy.shop.user.UserAggregate;

import java.util.UUID;

@Getter
@DomainEvent(name = "USER_CHANGE_EMAIL_EVENT")
public class UserChangeEmailEvent extends Event<UserAggregate> {
    private final UUID userId;
    private final String email;

    public UserChangeEmailEvent(UUID userId, String email) {
        super();
        this.userId = userId;
        this.email = email;
    }
}