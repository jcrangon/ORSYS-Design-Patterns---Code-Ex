import java.util.*;

public class CheckoutTddDemo {
    public static void main(String[] args) {
        TestSuite suite = new TestSuite();
        suite.test("confirms order when payment approved", CheckoutTddDemo::approved);
        suite.test("refuses order when payment declined", CheckoutTddDemo::declined);
        suite.test("sends confirmation email", CheckoutTddDemo::notification);
        suite.test("records order history", CheckoutTddDemo::history);
        suite.test("applies premium discount", CheckoutTddDemo::premiumDiscount);
        suite.summary();
    }

    static void approved() {
        Fixture f = Fixture.withPayment(true);
        Receipt r=f.service.confirm(new Order("O1", Customer.STANDARD, 100));
        Checks.equals("APPROVED", r.status(), "statut");
    }
    static void declined() {
        Fixture f = Fixture.withPayment(false);
        Receipt r=f.service.confirm(new Order("O2", Customer.STANDARD, 100));
        Checks.equals("DECLINED", r.status(), "statut");
        Checks.isTrue(!f.repo.exists("O2"), "ne pas persister si paiement refusé");
    }
    static void notification() {
        Fixture f = Fixture.withPayment(true);
        f.service.confirm(new Order("O3", Customer.STANDARD, 100));
        Checks.equals(List.of("O3"), f.notifier.sent, "notification");
    }
    static void history() {
        Fixture f = Fixture.withPayment(true);
        f.service.confirm(new Order("O4", Customer.STANDARD, 100));
        Checks.isTrue(f.repo.exists("O4"), "historique");
    }
    static void premiumDiscount() {
        Fixture f = Fixture.withPayment(true);
        Receipt r=f.service.confirm(new Order("O5", Customer.PREMIUM, 200));
        Checks.equals(170, r.charged(), "15 % de remise premium");
    }

    enum Customer { STANDARD, PREMIUM }
    record Order(String id, Customer customer, int total) {}
    record Receipt(String status, int charged) {}
    interface PaymentPort { boolean authorize(int amount); }
    interface OrderRepository { void save(Order order); boolean exists(String id); }
    interface Notifier { void confirmation(String orderId); }
    interface DiscountPolicy { int apply(Customer customer, int total); }

    static final class CheckoutService {
        private final PaymentPort payment; private final OrderRepository repo;
        private final Notifier notifier; private final DiscountPolicy discounts;
        CheckoutService(PaymentPort payment, OrderRepository repo, Notifier notifier, DiscountPolicy discounts) {
            this.payment=payment; this.repo=repo; this.notifier=notifier; this.discounts=discounts;
        }
        Receipt confirm(Order order) {
            int amount=discounts.apply(order.customer(), order.total());
            if (!payment.authorize(amount)) return new Receipt("DECLINED",0);
            repo.save(order); notifier.confirmation(order.id());
            return new Receipt("APPROVED",amount);
        }
    }
    static final class PaymentStub implements PaymentPort {
        final boolean approved; PaymentStub(boolean approved) { this.approved=approved; }
        public boolean authorize(int amount) { return approved; }
    }
    static final class InMemoryRepo implements OrderRepository {
        final Set<String> ids=new HashSet<>();
        public void save(Order order) { ids.add(order.id()); }
        public boolean exists(String id) { return ids.contains(id); }
    }
    static final class NotifierSpy implements Notifier {
        final List<String> sent=new ArrayList<>();
        public void confirmation(String orderId) { sent.add(orderId); }
    }
    static final class StandardDiscounts implements DiscountPolicy {
        public int apply(Customer customer,int total) { return customer==Customer.PREMIUM ? total*85/100 : total; }
    }
    static final class Fixture {
        final InMemoryRepo repo=new InMemoryRepo(); final NotifierSpy notifier=new NotifierSpy();
        final CheckoutService service;
        private Fixture(boolean approved) { service=new CheckoutService(new PaymentStub(approved),repo,notifier,new StandardDiscounts()); }
        static Fixture withPayment(boolean approved) { return new Fixture(approved); }
    }
    static final class TestSuite {
        int ok,ko;
        void test(String name, Runnable r) {
            try { r.run(); ok++; System.out.println("PASS " + name); }
            catch(Throwable t) { ko++; System.out.println("FAIL " + name + " -> " + t.getMessage()); }
        }
        void summary() {
            System.out.println("Suite: " + ok + " OK, " + ko + " KO");
            if(ko>0) throw new AssertionError("suite en échec");
        }
    }
}

final class Checks {
    private Checks() {}
    static void equals(Object expected, Object actual, String message) {
        if (!java.util.Objects.equals(expected, actual)) {
            throw new AssertionError(message + " | attendu=" + expected + ", obtenu=" + actual);
        }
    }
    static void isTrue(boolean condition, String message) {
        if (!condition) throw new AssertionError(message);
    }
    static void fails(Runnable action, Class<? extends Throwable> type, String message) {
        try { action.run(); }
        catch (Throwable t) {
            if (type.isInstance(t)) return;
            throw new AssertionError(message + " | exception=" + t, t);
        }
        throw new AssertionError(message + " | aucune exception");
    }
}
