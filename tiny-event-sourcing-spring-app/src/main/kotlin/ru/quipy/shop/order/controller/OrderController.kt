package ru.quipy.shop.order.controller

import org.springframework.web.bind.annotation.*
import ru.quipy.bankDemo.accounts.api.AccountAggregate
import ru.quipy.bankDemo.accounts.api.AccountCreatedEvent
import ru.quipy.bankDemo.accounts.api.BankAccountCreatedEvent
import ru.quipy.bankDemo.accounts.logic.Account
import ru.quipy.bankDemo.accounts.logic.BankAccount
import ru.quipy.core.EventSourcingService
import ru.quipy.shop.order.Order
import ru.quipy.shop.order.OrderAggregate
import ru.quipy.shop.order.entities.OrderStatus
import ru.quipy.shop.order.events.OrderChangeStatusEvent
import ru.quipy.shop.order.events.OrderCreatedEvent
import ru.quipy.shop.order.events.OrderProductAddedEvent
import ru.quipy.shop.order.events.OrderProductRemovedEvent
import ru.quipy.shop.product.Product
import ru.quipy.shop.product.ProductAggregate
import java.util.*

@RestController
@RequestMapping("/accounts")
class OrderController(
        val orderEsService: EventSourcingService<UUID, OrderAggregate, Order>,
        val productEsService: EventSourcingService<UUID, ProductAggregate, Product>
) {

    @PostMapping("/{orderId}")
    fun createOrder(@PathVariable userId: UUID) : OrderCreatedEvent {
        return orderEsService.create { it.createOrder(userId = userId) }
    }

    @GetMapping("/{orderId}")
    fun getOrder(@PathVariable orderId: UUID) : Order? {
        return orderEsService.getState(orderId)
    }

    @PostMapping("/{orderId}/add")
    fun addProduct(@PathVariable orderId: UUID, @RequestParam productId: UUID, @RequestParam quantity: Int) : OrderProductAddedEvent {
        val price = productEsService.getState(productId)!!.price
        return orderEsService.update(orderId) { it.addProduct(productId, quantity, price) }
    }

    @PostMapping("/{orderId}/delete")
    fun deleteProduct(@PathVariable orderId: UUID, @RequestParam productId: UUID) : OrderProductRemovedEvent {
        val product = productEsService.getState(productId)!!
        return orderEsService.update(orderId) { it.deleteProduct(product)}
    }

    @PostMapping("/{orderId}/finalize")
    fun finalizeOrder(@PathVariable orderId: UUID) : OrderChangeStatusEvent {
        return orderEsService.update(orderId) { it.changeStatus(OrderStatus.BOOKED)}
    }
}