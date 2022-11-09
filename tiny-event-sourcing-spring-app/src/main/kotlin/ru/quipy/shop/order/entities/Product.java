package ru.quipy.shop.order.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class Product {
    private final UUID id;
    private final String name;
    @Setter
    private Long price;
}
