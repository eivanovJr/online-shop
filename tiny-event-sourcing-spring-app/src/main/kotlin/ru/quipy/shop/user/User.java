package ru.quipy.shop.user;

import ru.quipy.core.annotations.StateTransitionFunc;
import ru.quipy.domain.AggregateState;
import ru.quipy.shop.order.Order;
import ru.quipy.shop.user.events.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User implements AggregateState<UUID, UserAggregate> {

    private final UUID id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String address;
    private final List<UUID> orderIds = new ArrayList<UUID>();

    public UUID getId() { return id; }

    public User(UUID id, String firstName, String lastName, String phone, String email, String address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    @StateTransitionFunc
    public void changeFirstName(UserChangeFirstNameEvent event) {
        firstName = event.getFirstName();
    }

    @StateTransitionFunc
    public void changeLastName(UserChangeLastNameEvent event) {
        lastName = event.getLastName();
    }

    @StateTransitionFunc
    public void changePhone(UserChangePhoneEvent event) {
        phone = event.getPhone();
    }

    @StateTransitionFunc
    public void changeEmail(UserChangeEmailEvent event) {
        email = event.getEmail();
    }

    @StateTransitionFunc
    public void changeAddress(UserChangeAddressEvent event) {
        address = event.getAddress();
    }

    @StateTransitionFunc
    public void addOrder(UserOrderAddedEvent event) {
        orderIds.add(event.getId());
    }

    @StateTransitionFunc
    public void deleteOrder(UserOrderRemovedEvent event) {
        if (!orderIds.contains(event.getId())) {
            throw new IllegalStateException("no such product in order");
        }
        orderIds.removeIf((orderId) -> orderId == event.getId());
    }

    public UserChangeFirstNameEvent changeFirstName(String newFirstName) {
        return new UserChangeFirstNameEvent(id, newFirstName);
    }

    public UserChangeLastNameEvent changeLastName(String newLastName) {
        return new UserChangeLastNameEvent(id, newLastName);
    }

    public UserChangePhoneEvent changePhone(String newPhone) {
        return new UserChangePhoneEvent(id, newPhone);
    }

    public UserChangeEmailEvent changeEmail(String newEmail) {
        return new UserChangeEmailEvent(id, newEmail);
    }

    public UserChangeAddressEvent changeAddress(String newAddress) {
        return new UserChangeAddressEvent(id, newAddress);
    }

    public UserOrderAddedEvent addOrder(Order order) {
        return new UserOrderAddedEvent(id, order);
    }

    public UserOrderRemovedEvent deleteOrder(Order order) {
        return new UserOrderRemovedEvent(id, order);
    }
}
