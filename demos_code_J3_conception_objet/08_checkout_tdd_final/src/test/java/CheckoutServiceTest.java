import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class CheckoutServiceTest {

    private InMemoryRepo repo;
    private NotifierSpy notifier;

    @BeforeEach
    void setUp() {
        repo = new InMemoryRepo();
        notifier = new NotifierSpy();
    }

    @Test
    void confirmsOrderWhenPaymentIsApproved() {
        CheckoutService service = serviceWithPayment(true);
        Receipt receipt = service.confirm(new Order("O1", Customer.STANDARD, 100));
        assertEquals("APPROVED", receipt.status());
        assertEquals(100, receipt.charged());
    }

    @Test
    void refusesOrderWhenPaymentIsDeclined() {
        CheckoutService service = serviceWithPayment(false);
        Receipt receipt = service.confirm(new Order("O2", Customer.STANDARD, 100));
        assertEquals("DECLINED", receipt.status());
        assertEquals(0, receipt.charged());
        assertFalse(repo.exists("O2"));
    }

    @Test
    void sendsConfirmationWhenApproved() {
        CheckoutService service = serviceWithPayment(true);
        service.confirm(new Order("O3", Customer.STANDARD, 100));
        assertEquals(List.of("O3"), notifier.sent);
    }

    @Test
    void recordsOrderHistoryWhenApproved() {
        CheckoutService service = serviceWithPayment(true);
        service.confirm(new Order("O4", Customer.STANDARD, 100));
        assertTrue(repo.exists("O4"));
    }

    @Test
    void appliesPremiumDiscountBeforePayment() {
        CheckoutService service = serviceWithPayment(true);
        Receipt receipt = service.confirm(new Order("O5", Customer.PREMIUM, 200));
        assertEquals(170, receipt.charged());
    }

    private CheckoutService serviceWithPayment(boolean approved) {
        return new CheckoutService(new PaymentStub(approved), repo, notifier, new StandardDiscounts());
    }

    static final class PaymentStub implements PaymentPort {
        private final boolean approved;
        PaymentStub(boolean approved) { this.approved = approved; }
        public boolean authorize(int amount) { return approved; }
    }

    static final class InMemoryRepo implements OrderRepository {
        private final Set<String> ids = new HashSet<>();
        public void save(Order order) { ids.add(order.id()); }
        public boolean exists(String id) { return ids.contains(id); }
    }

    static final class NotifierSpy implements Notifier {
        final List<String> sent = new ArrayList<>();
        public void confirmation(String orderId) { sent.add(orderId); }
    }
}
