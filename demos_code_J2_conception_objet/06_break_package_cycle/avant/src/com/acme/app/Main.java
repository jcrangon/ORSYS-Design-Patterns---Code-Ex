package com.acme.app;
import com.acme.orders.OrderService;
import com.acme.billing.BillingService;

public class Main {
    public static void main(String[] args) {
        BillingService billing = new BillingService();
        OrderService orders = new OrderService(billing);
        billing.setOrders(orders); // construction pénible : symptôme visible du cycle
        orders.cancel("O-42");
    }
}
