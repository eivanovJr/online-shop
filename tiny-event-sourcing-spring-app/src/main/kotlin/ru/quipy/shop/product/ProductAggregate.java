package ru.quipy.shop.product;

import ru.quipy.core.annotations.AggregateType;
import ru.quipy.domain.Aggregate;

@AggregateType(aggregateEventsTableName = "products")
public class ProductAggregate implements Aggregate {
}