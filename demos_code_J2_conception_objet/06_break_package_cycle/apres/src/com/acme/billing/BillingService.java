package com.acme.billing;
import com.acme.contracts.RefundListener;

public class BillingService {
    private final RefundListener listener;
    public BillingService(RefundListener listener) { this.listener = listener; }
    public void refund(String id) {
        System.out.println("billing: refund " + id);
        listener.refunded(id);
    }
}
