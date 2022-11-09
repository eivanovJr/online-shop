package ru.quipy.shop.delivery;


import ru.quipy.core.annotations.AggregateType;
import ru.quipy.domain.Aggregate;

@AggregateType(aggregateEventsTableName = "delivery")
public class DeliveryAggregate implements Aggregate {
}
