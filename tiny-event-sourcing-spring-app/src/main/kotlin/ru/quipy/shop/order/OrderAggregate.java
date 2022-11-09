package ru.quipy.shop.order;

import ru.quipy.core.annotations.AggregateType;
import ru.quipy.domain.Aggregate;

@AggregateType(aggregateEventsTableName = "orders")
public class OrderAggregate implements Aggregate {
}
