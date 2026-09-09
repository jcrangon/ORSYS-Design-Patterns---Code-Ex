public class CheckoutService {
    private final PaymentPort payment;
    private final OrderRepository repository;
    private final Notifier notifier;
    private final Audit audit;

    public CheckoutService(PaymentPort payment, OrderRepository repository,
                           Notifier notifier, Audit audit) {
        this.payment = payment;
        this.repository = repository;
        this.notifier = notifier;
        this.audit = audit;
    }

    public Receipt confirm(Order order) {
        if (!payment.pay(order)) {
            return new Receipt("DECLINED");
        }
        repository.save(order);
        notifier.confirmation(order);
        audit.publish("ORDER_CONFIRMED");
        return new Receipt("APPROVED");
    }
}
