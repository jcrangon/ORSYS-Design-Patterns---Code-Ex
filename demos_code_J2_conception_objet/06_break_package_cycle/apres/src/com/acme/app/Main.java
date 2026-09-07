package com.acme.app;
import com.acme.orders.OrderService;
import com.acme.orders.OrderRefundListener;
import com.acme.billing.BillingService;

public class Main {
    public static void main(String[] args) {
        OrderRefundListener listener = new OrderRefundListener();
        BillingService billing = new BillingService(listener);
        OrderService orders = new OrderService(billing);
        orders.cancel("O-42");
    }
}
