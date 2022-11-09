package ru.quipy.shop.user.events;

import lombok.Getter;
import ru.quipy.core.annotations.DomainEvent;
import ru.quipy.domain.Event;
import ru.quipy.shop.user.UserAggregate;

import java.util.UUID;

@Getter
@DomainEvent(name = "USER_CHANGE_LAST_NAME_EVENT")
public class UserChangeLastNameEvent extends Event<UserAggregate> {
    private final UUID userId;
    private final String lastName;

    public UserChangeLastNameEvent(UUID userId, String lastName) {
        super();
        this.userId = userId;
        this.lastName = lastName;
    }
}
