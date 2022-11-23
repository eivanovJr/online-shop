package ru.quipy.shop.order.subscribers

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import ru.quipy.core.EventSourcingService
import ru.quipy.shop.delivery.Delivery
import ru.quipy.shop.delivery.DeliveryAggregate
import ru.quipy.shop.delivery.DeliveryStatus
import ru.quipy.shop.delivery.events.DeliveryChangeStatusEvent
import ru.quipy.shop.order.Order
import ru.quipy.shop.order.OrderAggregate
import ru.quipy.shop.order.entities.OrderStatus
import ru.quipy.shop.payment.Payment
import ru.quipy.shop.payment.PaymentAggregate
import ru.quipy.shop.payment.entities.PaymentStatus
import ru.quipy.shop.payment.events.PaymentChangeStatusEvent
import ru.quipy.streams.AggregateSubscriptionsManager
import java.time.Instant
import java.util.*
import javax.annotation.PostConstruct

@Component
class PaymentSubscriber(
        private val subscriptionsManager: AggregateSubscriptionsManager,
        private val orderESService: EventSourcingService<UUID, OrderAggregate, Order>,
        private val paymentESService: EventSourcingService<UUID, PaymentAggregate, Payment>,
        private val deliveryESService: EventSourcingService<UUID, DeliveryAggregate, Delivery>
) {
    private val logger: Logger = LoggerFactory.getLogger(PaymentSubscriber::class.java)

    @PostConstruct
    fun init() {
        subscriptionsManager.createSubscriber(PaymentAggregate::class, "user::order-subscriber") {
            `when`(PaymentChangeStatusEvent::class) { event ->
                if (event.status == PaymentStatus.SUCCESSFULL) {
                    var payment = paymentESService.getState(event.paymentId)
                    var delivery = Delivery()
                    if (payment != null) {
                        orderESService.update(payment.getOrderId()) { order ->
                            order.changeStatus(OrderStatus.PAID)
                        }
                        delivery.createDelivery(UUID.randomUUID(), "address", 200, Date.from(Instant.now()), payment.getOrderId())
                    }
                }
            }
        }
    }
}