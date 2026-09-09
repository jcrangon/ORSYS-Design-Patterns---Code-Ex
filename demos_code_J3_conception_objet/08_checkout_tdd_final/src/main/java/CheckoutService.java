public class CheckoutService {
    private final PaymentPort payment;
    private final OrderRepository repository;
    private final Notifier notifier;
    private final DiscountPolicy discounts;

    public CheckoutService(PaymentPort payment, OrderRepository repository,
                           Notifier notifier, DiscountPolicy discounts) {
        this.payment = payment;
        this.repository = repository;
        this.notifier = notifier;
        this.discounts = discounts;
    }

    public Receipt confirm(Order order) {
        int amount = discounts.apply(order.customer(), order.total());
        if (!payment.authorize(amount)) {
            return new Receipt("DECLINED", 0);
        }
        repository.save(order);
        notifier.confirmation(order.id());
        return new Receipt("APPROVED", amount);
    }
}
