package ru.quipy.shop.payment;

import ru.quipy.core.annotations.AggregateType;
import ru.quipy.domain.Aggregate;

@AggregateType(aggregateEventsTableName = "payments")
public class PaymentAggregate implements Aggregate {
}
