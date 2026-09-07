import java.util.*;

public class DoublesDemo {
    public static void main(String[] args) {
        PaymentStub payment = new PaymentStub(true);                // Stub
        InMemoryOrderRepository repo = new InMemoryOrderRepository(); // Fake
        NotifierSpy notifier = new NotifierSpy();                   // Spy
        AuditMock audit = new AuditMock("ORDER_CONFIRMED");         // Mock manuel

        CheckoutService service = new CheckoutService(payment, repo, notifier, audit);
        Receipt receipt = service.confirm(new Order("O-42", 120));

        Checks.equals("APPROVED", receipt.status(), "statut reçu");
        Checks.isTrue(repo.exists("O-42"), "la commande doit être persistée");
        Checks.equals(1, notifier.confirmations.size(), "une confirmation");
        audit.verify();

        System.out.println("Stub  -> réponse paiement prédéfinie");
        System.out.println("Fake  -> repository mémoire réellement fonctionnel");
        System.out.println("Spy   -> interactions enregistrées: " + notifier.confirmations);
        System.out.println("Mock  -> attente vérifiée sur l'événement d'audit");
    }

    interface PaymentPort { boolean pay(Order order); }
    interface OrderRepository { void save(Order order); boolean exists(String id); }
    interface Notifier { void confirmation(Order order); }
    interface Audit { void publish(String event); }
    record Order(String id, int total) {}
    record Receipt(String status) {}

    static final class PaymentStub implements PaymentPort {
        private final boolean approved;
        PaymentStub(boolean approved) { this.approved = approved; }
        public boolean pay(Order order) { return approved; }
    }
    static final class InMemoryOrderRepository implements OrderRepository {
        private final Map<String,Order> data = new HashMap<>();
        public void save(Order order) { data.put(order.id(), order); }
        public boolean exists(String id) { return data.containsKey(id); }
    }
    static final class NotifierSpy implements Notifier {
        final List<String> confirmations = new ArrayList<>();
        public void confirmation(Order order) { confirmations.add(order.id()); }
    }
    static final class AuditMock implements Audit {
        private final String expected; private boolean seen;
        AuditMock(String expected) { this.expected = expected; }
        public void publish(String event) { if (expected.equals(event)) seen=true; }
        void verify() { Checks.isTrue(seen, "interaction d'audit attendue: " + expected); }
    }
    static final class CheckoutService {
        private final PaymentPort payment; private final OrderRepository repo;
        private final Notifier notifier; private final Audit audit;
        CheckoutService(PaymentPort payment, OrderRepository repo, Notifier notifier, Audit audit) {
            this.payment=payment; this.repo=repo; this.notifier=notifier; this.audit=audit;
        }
        Receipt confirm(Order order) {
            if (!payment.pay(order)) return new Receipt("DECLINED");
            repo.save(order); notifier.confirmation(order); audit.publish("ORDER_CONFIRMED");
            return new Receipt("APPROVED");
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
