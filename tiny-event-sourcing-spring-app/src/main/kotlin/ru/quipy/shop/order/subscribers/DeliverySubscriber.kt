package ru.quipy.shop.order.subscribers

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import ru.quipy.core.EventSourcingService
import ru.quipy.shop.delivery.Delivery
import ru.quipy.shop.delivery.DeliveryAggregate
import ru.quipy.shop.delivery.DeliveryStatus
import ru.quipy.shop.delivery.events.DeliveryChangeStatusEvent
import ru.quipy.shop.delivery.events.DeliveryCreateEvent
import ru.quipy.shop.order.Order
import ru.quipy.shop.order.OrderAggregate
import ru.quipy.shop.order.entities.OrderStatus
import ru.quipy.shop.order.events.OrderCreatedEvent
import ru.quipy.shop.product.ProductAggregate
import ru.quipy.shop.product.events.ProductChangePrice
import ru.quipy.shop.user.User
import ru.quipy.shop.user.UserAggregate
import ru.quipy.shop.user.subscribers.OrderSubscriber
import ru.quipy.streams.AggregateSubscriptionsManager
import java.util.*
import javax.annotation.PostConstruct

@Component
class DeliverySubscriber(
        private val subscriptionsManager: AggregateSubscriptionsManager,
        private val orderESService: EventSourcingService<UUID, OrderAggregate, Order>,
        private val deliveryESService: EventSourcingService<UUID, DeliveryAggregate, Delivery>
) {
    private val logger: Logger = LoggerFactory.getLogger(DeliverySubscriber::class.java)

    @PostConstruct
    fun init() {
        subscriptionsManager.createSubscriber(DeliveryAggregate::class, "user::delivery-subscriber") {
            `when`(DeliveryChangeStatusEvent::class) { event ->
                if (event.status == DeliveryStatus.DELIVERED) {
                    var delivery = deliveryESService.getState(event.deliveryId)
                    if (delivery != null) {
                        orderESService.update(delivery.getOrderId()) {
                            it.changeStatus(OrderStatus.COMPLETED)
                        }
                    }
                } else if (event.status == DeliveryStatus.BOOKED || event.status == DeliveryStatus.DELAYED) {
                    var delivery = deliveryESService.getState(event.deliveryId)
                    if (delivery != null) {
                        orderESService.update(delivery.getOrderId()) {
                            it.changeStatus(OrderStatus.SHIPPING)
                        }
                    }
                }
            }

            `when`(DeliveryCreateEvent::class) { event ->
                orderESService.update(event.orderId) { order ->
                    order.setDelivery(event.deliveryId)
                }
            }
        }
    }
}