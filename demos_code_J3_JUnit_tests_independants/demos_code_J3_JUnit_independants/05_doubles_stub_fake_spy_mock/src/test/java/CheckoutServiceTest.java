import org.junit.jupiter.api.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class CheckoutServiceTest {

    @Test
    void demonstratesStubFakeSpyAndManualMock() {
        PaymentStub payment = new PaymentStub(true);             // STUB
        InMemoryOrderRepository repo = new InMemoryOrderRepository(); // FAKE
        NotifierSpy notifier = new NotifierSpy();                // SPY
        AuditMock audit = new AuditMock("ORDER_CONFIRMED");       // MOCK manuel

        CheckoutService service = new CheckoutService(payment, repo, notifier, audit);
        Receipt receipt = service.confirm(new Order("O-42", 120));

        assertEquals("APPROVED", receipt.status());
        assertTrue(repo.exists("O-42"));
        assertEquals(List.of("O-42"), notifier.confirmations);
        assertTrue(audit.wasExpectedEventPublished());
    }

    static final class PaymentStub implements PaymentPort {
        private final boolean approved;
        PaymentStub(boolean approved) { this.approved = approved; }
        public boolean pay(Order order) { return approved; }
    }

    static final class InMemoryOrderRepository implements OrderRepository {
        private final Map<String, Order> data = new HashMap<>();
        public void save(Order order) { data.put(order.id(), order); }
        public boolean exists(String id) { return data.containsKey(id); }
    }

    static final class NotifierSpy implements Notifier {
        final List<String> confirmations = new ArrayList<>();
        public void confirmation(Order order) { confirmations.add(order.id()); }
    }

    static final class AuditMock implements Audit {
        private final String expected;
        private boolean seen;
        AuditMock(String expected) { this.expected = expected; }
        public void publish(String event) { seen = seen || expected.equals(event); }
        boolean wasExpectedEventPublished() { return seen; }
    }
}
