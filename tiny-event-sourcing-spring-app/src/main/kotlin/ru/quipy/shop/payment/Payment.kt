package ru.quipy.shop.payment

import ru.quipy.core.annotations.StateTransitionFunc
import ru.quipy.domain.AggregateState
import ru.quipy.shop.order.OrderAggregate
import ru.quipy.shop.order.events.OrderCreatedEvent
import ru.quipy.shop.payment.entities.PaymentStatus
import ru.quipy.shop.payment.events.PaymentChangeStatusEvent
import ru.quipy.shop.payment.events.PaymentCreateEvent
import ru.quipy.shop.payment.events.PaymentSetDateEvent

import java.util.Date
import java.util.Optional
import java.util.UUID

class Payment(
    private var id: UUID,
    private var paymentDate: Optional<Date> = Optional.empty(),
    private var totalPrice: Long,
    private var status: PaymentStatus = PaymentStatus.AWAITING
) : AggregateState<UUID, PaymentAggregate> {

    override fun getId(): UUID? = id

    @StateTransitionFunc
    fun createPayment(event: PaymentCreateEvent) {
        id = event.paymentId
        totalPrice = event.totalPrice
    }

    @StateTransitionFunc
    fun changeStatus(event: PaymentChangeStatusEvent) {
        status = event.status
    }

    @StateTransitionFunc
    fun setPaymentDate(event: PaymentSetDateEvent) {
        paymentDate = Optional.of(event.date)
    }

    fun createPayment(totalPrice: Long): PaymentCreateEvent =
        PaymentCreateEvent(id, totalPrice)

    fun changeStatus(status: PaymentStatus): PaymentChangeStatusEvent =
        PaymentChangeStatusEvent(id, status)

    fun setPaymentDate(date: Date): PaymentSetDateEvent =
        if (paymentDate.isPresent || status == PaymentStatus.SUCCESSFULL)
            throw IllegalStateException("Payment was already completed")
        else
            PaymentSetDateEvent(date, id)

}
