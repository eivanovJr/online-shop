package ru.quipy.shop.order.events

import ru.quipy.core.annotations.DomainEvent
import ru.quipy.domain.Event
import ru.quipy.shop.order.OrderAggregate
import ru.quipy.shop.order.entities.OrderStatus

import java.util.UUID

@DomainEvent(name = "ORDER_CHANGE_STATUS_EVENT")
data class OrderChangeStatusEvent(
    private val status: OrderStatus,
    private val orderId: UUID
) : Event<OrderAggregate>(
    name = "ORDER_CHANGE_STATUS_EVENT"
)
