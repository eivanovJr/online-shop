package ru.quipy.shop.user.subscribers

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import ru.quipy.bankDemo.accounts.api.AccountAggregate
import ru.quipy.bankDemo.accounts.logic.Account
import ru.quipy.bankDemo.transfers.api.TransactionConfirmedEvent
import ru.quipy.bankDemo.transfers.api.TransferTransactionAggregate
import ru.quipy.bankDemo.transfers.api.TransferTransactionCreatedEvent
import ru.quipy.core.EventSourcingService
import ru.quipy.shop.order.OrderAggregate
import ru.quipy.shop.order.events.OrderChangeStatusEvent
import ru.quipy.shop.order.events.OrderCreatedEvent
import ru.quipy.shop.user.User
import ru.quipy.shop.user.UserAggregate
import ru.quipy.streams.AggregateSubscriptionsManager
import java.util.*
import javax.annotation.PostConstruct

@Component
class OrderSubscriber(
        private val subscriptionsManager: AggregateSubscriptionsManager,
        private val userESService: EventSourcingService<UUID, UserAggregate, User>
) {
    private val logger: Logger = LoggerFactory.getLogger(OrderSubscriber::class.java)

    @PostConstruct
    fun init() {
        subscriptionsManager.createSubscriber(OrderAggregate::class, "user::order-subscriber") {
            `when`(OrderCreatedEvent::class) { event ->

                userESService.update(event.userId) {
                    it.addOrder(event.orderId)
                }

                logger.info("Order: ${event.orderId}. Owner: ${event.userId}")
            }
        }
    }
}