package ru.quipy.shop.user.events;

import lombok.Getter;
import ru.quipy.core.annotations.DomainEvent;
import ru.quipy.domain.Event;
import ru.quipy.shop.user.UserAggregate;

import java.util.UUID;

@Getter
@DomainEvent(name = "USER_CHANGE_FIRST_NAME_EVENT")
public class UserChangeFirstNameEvent extends Event<UserAggregate> {
    private final UUID userId;
    private final String firstName;

    public UserChangeFirstNameEvent(UUID userId, String firstName) {
        super();
        this.userId = userId;
        this.firstName = firstName;
    }
}
