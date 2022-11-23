package ru.quipy.shop.user

import ru.quipy.core.annotations.StateTransitionFunc
import ru.quipy.domain.AggregateState
import ru.quipy.shop.order.Order
import ru.quipy.shop.user.events.*

import java.util.ArrayList
import java.util.UUID

class User(
    private val id: UUID,
    var firstName: String,
    var lastName: String,
    var phone: String,
    var email: String,
    var address: String,
    val orderIds: MutableList<UUID> = mutableListOf()
) : AggregateState<UUID, UserAggregate> {


    override fun getId(): UUID? = id

    @StateTransitionFunc
    fun changeFirstName(event: UserChangeFirstNameEvent) {
        firstName = event.firstName
    }

    @StateTransitionFunc
    fun changeLastName(event: UserChangeLastNameEvent) {
        lastName = event.lastName
    }

    @StateTransitionFunc
    fun changePhone(event: UserChangePhoneEvent) {
        phone = event.phone
    }

    @StateTransitionFunc
    fun changeEmail(event: UserChangeEmailEvent) {
        email = event.email
    }

    @StateTransitionFunc
    fun changeAddress(event: UserChangeAddressEvent) {
        address = event.address
    }

    @StateTransitionFunc
    fun addOrder(event: UserOrderAddedEvent) {
        orderIds.add(event.orderId)
    }

    @StateTransitionFunc
    fun deleteOrder(event: UserOrderRemovedEvent) {
        if (!orderIds.contains(event.id)) {
            throw IllegalStateException("no such product in order")
        }
        orderIds.removeIf { it == event.id }
    }

    fun changeFirstName(newFirstName: String): UserChangeFirstNameEvent =
        UserChangeFirstNameEvent(id, newFirstName)

    fun changeLastName(newLastName: String): UserChangeLastNameEvent =
        UserChangeLastNameEvent(id, newLastName)

    fun changePhone(newPhone: String): UserChangePhoneEvent =
        UserChangePhoneEvent(id, newPhone)

    fun changeEmail(newEmail: String): UserChangeEmailEvent =
        UserChangeEmailEvent(id, newEmail)

    fun changeAddress(newAddress: String): UserChangeAddressEvent =
        UserChangeAddressEvent(id, newAddress)

    fun addOrder(orderId: UUID): UserOrderAddedEvent =
        UserOrderAddedEvent(id, orderId)

    fun deleteOrder(order: Order): UserOrderRemovedEvent =
        UserOrderRemovedEvent(id, order)
}
