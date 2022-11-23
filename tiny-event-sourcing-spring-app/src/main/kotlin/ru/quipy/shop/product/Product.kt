package ru.quipy.shop.product

import lombok.AllArgsConstructor
import lombok.Getter
import lombok.Setter
import ru.quipy.core.annotations.StateTransitionFunc
import ru.quipy.domain.AggregateState
import ru.quipy.shop.payment.entities.PaymentStatus
import ru.quipy.shop.payment.events.PaymentChangeStatusEvent
import ru.quipy.shop.product.events.ProductChangePrice
import ru.quipy.shop.product.events.ProductCreateEvent

import java.util.UUID

@Getter
@AllArgsConstructor
class Product(
    private var id: UUID,
    val name: String,
    @Setter
    var price: Long
) : AggregateState<UUID, ProductAggregate> {

    @StateTransitionFunc
    fun createProduct(event: ProductCreateEvent) {
        id = event.productId
    }

    @StateTransitionFunc
    fun changePrice(event: ProductChangePrice) {
        price = event.price
    }

    fun changeStatus(price: Long): ProductChangePrice =
        ProductChangePrice(id, price)

    fun createProduct(totalPrice: Long): ProductCreateEvent =
        ProductCreateEvent(id, price)

    override fun getId(): UUID? = id
}
