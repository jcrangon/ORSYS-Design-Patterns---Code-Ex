package com.acme.orders.internal;

// Public ici uniquement pour permettre l'exemple entre sous-packages Java.
// Le package `internal` est une convention de frontière : l'application ne l'importe pas.
public final class OrderRepository {
    public String insert(String customer) {
        return "O-" + Math.abs(customer.hashCode());
    }
}
