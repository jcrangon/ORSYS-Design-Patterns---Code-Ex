package com.acme.billing;
import com.acme.orders.OrderService;

public class BillingService {
    private OrderService orders;
    public void setOrders(OrderService orders) { this.orders = orders; }
    public void refund(String id) {
        System.out.println("billing: refund " + id);
        orders.markRefunded(id);
    }
}
