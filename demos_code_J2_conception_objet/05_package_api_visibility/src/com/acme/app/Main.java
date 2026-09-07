package com.acme.app;

import com.acme.orders.api.OrderService;
// import com.acme.orders.internal.OrderRepository; // Décommentez pour montrer l'odeur architecturale.

public class Main {
    public static void main(String[] args) {
        OrderService orders = new OrderService();
        System.out.println("Créée : " + orders.create("Alice"));
    }
}
