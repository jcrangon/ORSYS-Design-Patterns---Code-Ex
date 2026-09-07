package com.acme.orders;
import com.acme.contracts.RefundListener;

public class OrderRefundListener implements RefundListener {
    public void refunded(String id) { System.out.println("orders: refunded " + id); }
}
