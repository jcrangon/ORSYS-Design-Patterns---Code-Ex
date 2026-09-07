package com.acme.orders.api;

import com.acme.orders.internal.OrderRepository;

public final class OrderService {
    private final OrderRepository repository = new OrderRepository();

    public String create(String customer) {
        return repository.insert(customer);
    }
}
