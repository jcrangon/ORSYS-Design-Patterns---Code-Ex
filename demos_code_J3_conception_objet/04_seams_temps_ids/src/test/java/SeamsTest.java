import org.junit.jupiter.api.Test;
import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class SeamsTest {

    private static final Instant NOW = Instant.parse("2026-09-06T10:00:00Z");
    private static final UUID ID = UUID.fromString("00000000-0000-0000-0000-000000000001");

    @Test
    void clockCanBeControlled() {
        ClockPort fixedClock = () -> NOW;
        CouponService service = new CouponService(fixedClock);

        assertTrue(service.validUntil(Instant.parse("2026-09-07T00:00:00Z")));
        assertFalse(service.validUntil(Instant.parse("2026-09-06T09:00:00Z")));
    }

    @Test
    void idAndCreationTimeAreDeterministic() {
        ClockPort fixedClock = () -> NOW;
        IdGenerator fixedIds = () -> ID;
        OrderFactory factory = new OrderFactory(fixedIds, fixedClock);

        Order order = factory.create(120);

        assertEquals(ID, order.id());
        assertEquals(NOW, order.createdAt());
        assertEquals(120, order.total());
    }
}
