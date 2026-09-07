package com.acme.orders;
import com.acme.billing.BillingService;

public class OrderService {
    private final BillingService billing;
    public OrderService(BillingService billing) { this.billing = billing; }
    public void cancel(String id) {
        System.out.println("orders: cancel " + id);
        billing.refund(id);
    }
    public void markRefunded(String id) { System.out.println("orders: refunded " + id); }
}
